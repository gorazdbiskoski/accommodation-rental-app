import './App.css'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {HomePage} from "./ui/pages/HomePage/HomePage.jsx";
import {CountriesPage} from "./ui/pages/CountriesPage/CountriesPage.jsx";
import {HostsPage} from "./ui/pages/HostsPage/HostsPage.jsx";
import {Header} from "./ui/components/layout/Header/Header.jsx";
import {AccommodationsPage} from "./ui/pages/AccommodationPage/AccommodationsPage.jsx";

function App() {

  return (
      <BrowserRouter>
          <Header/>
          <Routes>
              <Route index element={<HomePage/>}/>
              <Route path={"/accommodations"} element={<AccommodationsPage/>} />
              <Route path={"/hosts"} element={<HostsPage/>} />
              <Route path={"/countries"} element={<CountriesPage/>} />
              {/*<Route path={"/signin"} element={<SignInPage/>}></Route>*/}
          </Routes>
      </BrowserRouter>
  )
}

export default App
