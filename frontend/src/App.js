import React, {useState, useEffect} from 'react';
import { BrowserRouter as Router} from 'react-router-dom';
import {Routes, Route} from 'react-router-dom';
import Home from './page/Home';

import AuthPage from "./page/AuthPage";

function App() {
  return (
    <Router>
      <div>
        <section>                              
            <Routes>                                                                        
              {/* <Route path="/" element={<Home/>}/> */}
              <Route path="/auth" element={<AuthPage/>}/>
            </Routes>                    
        </section>
      </div>
    </Router>
  )
}

export default App
