import {
  BrowserRouter as Router,
  Route,
  Routes,
} from "react-router-dom";


import Sidebar from "./components/Sidebar";
import View from "./components/View";
import NewRentalButton from "./components/NewRentalButton";

import Rentals from "./pages/Rentals";
import Customers from "./pages/Customers";
import Games from "./pages/Games";
import NewRental from "./pages/NewRental";
import Customer from "./pages/Customer";
import NewCustomer from "./pages/NewCustomer";
import NewGame from "./pages/NewGame";

import "./assets/styles/reset.css";
import "./assets/styles/style.css";

export default function App() {
  return (
    <Router>
      <View>
        <Sidebar />

        <Routes>
          <Route path="/" element={<Rentals/>}>
          </Route>

          <Route path="/rentals" element={<Rentals />}>
            
          </Route>

          <Route path="/customers" element={<Customers />}>
            
          </Route>

          <Route path="/games" element={<Games />}>
            
          </Route>

          <Route path="/rentals/new" element={ <NewRental />}>
           
          </Route>

          <Route path="/customers/new" element={<NewCustomer />}>
            
          </Route>

          <Route path="/customers/:customerId" element={<Customer />}>
            
          </Route>

          <Route path="/games/new" element={<NewGame />}>
            
          </Route>
        </Routes>

        <NewRentalButton />
      </View>
    </Router>
  );
}
