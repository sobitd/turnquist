import React, {useState, useEffect} from "react"

function ListOfVideos() {
    const [videos, setVideos] = useState([]);
    useEffect(() => {
      fetch("http://localhost:8080/api/videos")
      .then(response => response.json())
      .then(data => setVideos(data))
      .catch(error => console.error("Error fetching videos:", error));
    }, [])
    
    return (
        <>
        <ul>
            {videos.map((item, index) => (
                 <li key={index}>{item.name}</li>
            ))}
        </ul>
        </>
    )
}

export default ListOfVideos;