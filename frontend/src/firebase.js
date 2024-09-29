// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyDiKbSuLu73Of6uihFiIgOH6_GCLT-XX24",
  authDomain: "diagnose-ai-f8919.firebaseapp.com",
  projectId: "diagnose-ai-f8919",
  storageBucket: "diagnose-ai-f8919.appspot.com",
  messagingSenderId: "271521758059",
  appId: "1:271521758059:web:fc23a9fbadf027f0376a2e",
};

const app = initializeApp(firebaseConfig);

// Initialize Firebase Authentication and Firestore
const auth = getAuth(app);
const firestore = getFirestore(app);

export { auth, firestore };
