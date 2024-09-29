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


export default function Chat() {
    const [messages, setMessages] = useState([]);
    const [msgText, setMsgText] = useState('');
    function SendMessage(){  
        setMessages([...messages, msgText]);      
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
                          Chat Title
                        </p>
                      </div>
                    </div>
                    <div className="pt-1">
                      <p className="small mb-1 text-white">Just now</p>
                    </div>
                  </a>
                </li>
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
            <MDBBtn onClick={()=>SendMessage()} color="light" size="lg" rounded className="float-end">
              Send
            </MDBBtn>
          </MDBTypography>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}