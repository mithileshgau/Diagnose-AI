// useAuthRedirect.js
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { onAuthStateChanged } from 'firebase/auth';
import { auth } from './firebase'; // Adjust the path as necessary

const useAuthRedirect = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const unsubscribe = onAuthStateChanged(auth, (user) => {
      if (user) {
        // User is signed in, navigate to /chat
        navigate('/chat');
      } else {
        // User is signed out, navigate to /auth
        navigate('/auth');
      }
    });

    // Cleanup subscription on unmount
    return () => unsubscribe();
  }, [navigate]);
};

export default useAuthRedirect;