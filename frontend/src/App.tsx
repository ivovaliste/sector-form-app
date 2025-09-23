import "./App.css";
import { InfoText } from "./components";
import UserForm from "./form/UserForm";

function App() {
  return (
    <div className="App">
      <div className="mt-4">
        <InfoText text="Please enter your name and pick the Sectors you are currently involved " />
      </div>

      <UserForm />
    </div>
  );
}

export default App;
