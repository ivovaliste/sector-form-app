import "./App.css";
import UserForm from "./form/UserForm";

function App() {
  return (
    <div className="App">
      <div className="mt-4">
        Please enter your name and pick the Sectors you are currently involved
        in.
      </div>

      <UserForm />
    </div>
  );
}

export default App;
