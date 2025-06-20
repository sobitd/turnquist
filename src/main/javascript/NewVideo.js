import React, {useState} from "react"

function NewVideo() {
    const [video, setVideo] = useState("");

    const handleChange = (event) => {
        setVideo(event.target.value);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        await fetch("http://localhost:8080/api/videos", {
            method: "POST",
            headers: {
                "Content-type": "application/json",
            },
            body: JSON.stringify({name: video }),
        });
        window.location.href = "/react";
    };
    
    return (
        <>
        <form onSubmit={handleSubmit}>
            <input type="text" value={video} onChange={handleChange}/>
            <button type="submit">Submit</button>
        </form>
        </>
    );
}

export default NewVideo;