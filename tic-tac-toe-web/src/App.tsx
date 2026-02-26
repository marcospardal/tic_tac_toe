import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Home from './pages/Home/Home'
import Game from './pages/Game/Game'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Home />} path='/'/>
        <Route element={<Game />} path='/game'/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
