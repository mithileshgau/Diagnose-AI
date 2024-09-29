import React,{useState,useEffect} from "react";
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBIcon,
  MDBBtn,
  MDBTypography,
  MDBTextArea,
  MDBCardHeader,
} from "mdb-react-ui-kit";
import "./Chat.css";
import { collection,addDoc,onSnapshot,doc,query,where } from "firebase/firestore";
import { firestore,auth } from "../../firebase";
import { onAuthStateChanged } from "firebase/auth";
import { signOut } from "firebase/auth";
import { useNavigate } from "react-router-dom";
let unsubscribe;
export default function Chat() {
  const navigate = useNavigate();
    const [messages, setMessages] = useState([]);
    const [msgText, setMsgText] = useState('');
    const [chatEnabled, setChatEnabled] = useState(true);
    const [convId, setConvId] = useState('');
    const [userId, setuserId] = useState('')
    const [conversations, setConversations] = useState([])
    const [responseIncoming, setResponseIncoming] = useState(false);
    useEffect(() => {
     var ismounted=true
     if (ismounted)
     {
        onAuthStateChanged(auth, (user) => {
          if (user) {
            const uid = user.uid;
            setuserId(uid)
            const itemsCollection = collection(firestore, "Conversations");
            const q = query(itemsCollection, where("user", "==", uid));
        
            unsubscribe = onSnapshot(q, (querySnapshot) => {
              const fetchedItems = querySnapshot.docs.map(doc => ({
                id: doc.id,
                "Title":doc.data().Title,
                "Date":firestoreTimestampToDaysAgo(doc.data().Date),
                "user":doc.data().user
              }));
              console.log(fetchedItems);
              setConversations(fetchedItems);
            }, (error) => {
              console.error("Error listening to query: ", error);
            });
          } else {
            navigate('/');
            console.log("user is logged out")
          }
        });
     }
    
      return () => {
        ismounted=false
        if (unsubscribe){
          unsubscribe();
        }
      }
    }, [])
    function firestoreTimestampToDaysAgo(timestamp) {
      // Convert Firestore timestamp to JavaScript Date
      const milliseconds = timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000;
      const date = new Date(milliseconds);
      // Calculate difference in days
      const currentDate = new Date();
      const differenceInTime = currentDate.getTime() - date.getTime();
      const differenceInDays = Math.floor(differenceInTime / (1000 * 3600 * 24));
    
      // Format as string
      if (differenceInDays === 0) {
        return "Today";
      }else{
        return `${differenceInDays} days ago`;
      }
      
    }
    const handleLogout = async () => {
      try {
        await signOut(auth);
        navigate('/');
      }
      catch (error) {
        console.error(error);
      }
    }
    async function SendMessage(){  
        setChatEnabled(false);
        setResponseIncoming(true);
        setMessages([...messages, msgText]);    
        var document=await addDoc(collection(firestore, "Conversations"), {
          Title:"New Conversation",
          "Date":new Date(),     
          user:userId     
       })
       setConvId(document.id)
       const mainDocRef = doc(firestore, "Conversations", document.id);
       const subcollectionRef = collection(mainDocRef, "Messages");
       const newDocRef = await addDoc(subcollectionRef, {
          "Message":msgText,
          "Sender":userId,
          "Date":new Date()
       });
         
        // fetch('http://localhost:3001/chat',{
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: JSON.stringify({msg: msgText})
        // }).then(res=>res.json()).then(data=>{
            
        //     setMsgText('');
        // })
    }
  return (
    <MDBContainer style={{ width: '100%', height: '100vh',maxWidth:'11000px' }} className="py-5 gradient-custom">
      <MDBRow>
        <MDBCol md="6" lg="5" xl="4" className="mb-4 mb-md-0">
          <h5 className="font-weight-bold mb-3 text-center text-white">
            Chat History
          </h5>

          <MDBCard className="mask-custom">
            <MDBCardBody>
              <MDBTypography listUnStyled className="mb-0">
                {conversations.map((conv,index)=>(
                    <li
                    className="p-2 border-bottom"
                    style={{
                      borderBottom: "1px solid rgba(255,255,255,.3) !important",
                    }}
                  >
                    <a
                      href="#!"
                      className="d-flex justify-content-between link-light"
                    >
                      <div className="d-flex flex-row">
                        <img
                          src="chat-icon.png"
                          alt="avatar"
                          className="rounded-circle d-flex align-self-center me-3 shadow-1-strong"
                          width="60"
                        />
                        
                        <div className="pt-1">
                          <p className="small text-white">
                            {conv.Title}
                          </p>
                        </div>
                      </div>
                      <div className="pt-1">
                        <p className="small mb-1 text-white">{conv.Date}</p>
                      </div>
                    </a>
                  </li>
                ))}
                {/* <li
                  className="p-2 border-bottom"
                  style={{
                    borderBottom: "1px solid rgba(255,255,255,.3) !important",
                  }}
                >
                  <a
                    href="#!"
                    className="d-flex justify-content-between link-light"
                  >
                    <div className="d-flex flex-row">
                      <img
                        src="chat-icon.png"
                        alt="avatar"
                        className="rounded-circle d-flex align-self-center me-3 shadow-1-strong"
                        width="60"
                      />
                      
                      <div className="pt-1">
                        <p className="small text-white">
                          Chat Title
                        </p>
                      </div>
                    </div>
                    <div className="pt-1">
                      <p className="small mb-1 text-white">Just now</p>
                    </div>
                  </a>
                </li> */}
              </MDBTypography>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>

        <MDBCol md="6" lg="7" xl="8">
          <MDBTypography listUnStyled className="text-white">
            {
                messages.map((msg,index)=>(
                    
                        <li className="d-flex justify-content-between mb-4">
                            <img
                            src="user.png"
                            alt="avatar"
                            className="rounded-circle d-flex align-self-start me-3 shadow-1-strong"
                            width="60"
                            />
                            <MDBCard className="mask-custom">
                            <MDBCardHeader
                                className="d-flex justify-content-between p-3"
                                style={{ borderBottom: "1px solid rgba(255,255,255,.3)" }}
                            >
                                <p className="fw-bold mb-0">User</p>
                                <p className="text-light small mb-0">
                                <MDBIcon far icon="clock" /> 12 mins ago
                                </p>
                            </MDBCardHeader>
                            <MDBCardBody>
                                <p className="mb-0">
                                {msg}
                                </p>
                            </MDBCardBody>
                            </MDBCard>
                        </li>
                    )
                )
            }
            <li className="mb-3">
              <MDBTextArea value={msgText} onChange={(e)=>{setMsgText(e.target.value)}} label="Message" id="textAreaExample" rows={4} />
            </li>
            <MDBBtn disabled={!chatEnabled} onClick={()=>SendMessage()} color="light" size="lg" rounded className="float-end">
              Send
            </MDBBtn>

            <MDBBtn disabled={!chatEnabled} onClick={()=>handleLogout()} color="light" size="lg" rounded className="float-end">
              Logout
            </MDBBtn>
          </MDBTypography>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}