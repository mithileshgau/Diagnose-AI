import React, {useState, useEffect} from 'react';
import { BrowserRouter as Router} from 'react-router-dom';
import {Routes, Route} from 'react-router-dom';
<<<<<<< HEAD
import useAuthRedirect from './components/hooks/useAuthRedirect';
import Chat from './components/Chat/Chat';
=======

>>>>>>> 128dd739391c677e372bde30195e79e9323c5eb0
import AuthPage from "./page/AuthPage";
import Chat from './components/Chat/Chat';

function App() {
  

  return (
    <Router>
      <div>
        <section>                              
            <Routes> 
              <Route exact path="/" element={<AuthPage/>}/>                                                                       
              <Route exact path="/chat" element={<Chat/>}/>
            </Routes>                    
        </section>
      </div>
    </Router>
  )
}

export default App
