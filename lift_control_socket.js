//import fetch from "node-fetch";

fetch('http://192.168.2.132:13000/service', { // iot ip
    method: 'POST',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify( 
        {"service":"lift_control", "cmd":
            {
                "lift_socket_ip": "192.168.2.100", // esp ip
                "lift_button_id": 0
            } 
        })
})
.then(response => response.json())
.then(response => console.log(JSON.stringify(response)))