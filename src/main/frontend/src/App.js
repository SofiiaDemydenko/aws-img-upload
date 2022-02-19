import React, {useState, useEffect, useCallback} from "react";
import './App.css';
import axios from "axios";
import {useDropzone} from "react-dropzone";

//functional component.
const UserProfiles = () => {
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/user-profile").then(res => {
      console.log(res);
      setUserProfiles(res.data);
    });
  };

  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index) => {
    return(
        <div key={index}>
          {/*todo: profile image*/}
          <br/>
          <br/>
          <h1>{userProfile.username}</h1>
          <p>{userProfile.id}</p>
          <Dropzone userId={userProfile.id}/>
          <br/>
        </div>
    );
  });
};

function Dropzone({userId}) {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);

    const formData = new FormData();
    formData.append("file", file);    //the same name ('file') as in @RequestParam in controller method.
    axios.post(
        `http://localhost:8080/user-profile/${userId}/image/upload`,
        formData,
        {
            "Content-Type": "multipart/form-data"
        }
    ).then(() => {
        console.log("file uploaded successfully");
    }).catch(err => {
        console.log(err);
    });
  }, []);

  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
          isDragActive ?
              <p>Drop the files here ...</p> :
              <p>Drag 'n' drop some profile image here, or click to select proper file</p>
        }
      </div>
  );
}

function App() {
  return (
    <div className="App">
      <UserProfiles/>
    </div>
  );
}

export default App;
