import React from "react";
import { createUserWithEmailAndPassword } from "firebase/auth";
import { collection,addDoc } from "firebase/firestore";
import { auth, firestore } from "../../firebase";

function SignUpForm() {
  const [state, setState] = React.useState({
    name: "",
    email: "",
    password: ""
  });

  const handleChange = evt => {
    const value = evt.target.value;
    setState({
      ...state,
      [evt.target.name]: value
    });
  };

  const handleOnSubmit = async evt => {
    evt.preventDefault();

    const { name, email, password } = state;

    try {
    console.log(auth)
      const userCredential = await createUserWithEmailAndPassword(auth,email, password);
      const user = userCredential.user;

      // Store additional user data in Firestore
    //   await firestore.collection("users").doc(user.uid).set({
    //     name,
    //     email
    //   });
    await addDoc(collection(firestore, "users"), {
        name:name,
        email:email
    })
      alert("User signed up successfully!");

      // Clear the form
      setState({
        name: "",
        email: "",
        password: ""
      });
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="form-container sign-up-container">
      <form onSubmit={handleOnSubmit}>
        <h1>Create Account</h1>
        {/* <div className="social-container">
          <a href="#" className="social">
            <i className="fab fa-facebook-f" />
          </a>
          <a href="#" className="social">
            <i className="fab fa-google-plus-g" />
          </a>
          <a href="#" className="social">
            <i className="fab fa-linkedin-in" />
          </a>
        </div>
        <span>or use your email for registration</span> */}
        <input
          type="text"
          name="name"
          value={state.name}
          onChange={handleChange}
          placeholder="Name"
        />
        <input
          type="email"
          name="email"
          value={state.email}
          onChange={handleChange}
          placeholder="Email"
        />
        <input
          type="password"
          name="password"
          value={state.password}
          onChange={handleChange}
          placeholder="Password"
        />
        <button>Sign Up</button>
      </form>
    </div>
  );
}

export default SignUpForm;