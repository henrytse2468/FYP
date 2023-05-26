import fetch from "node-fetch";

fetch("https://ive0001.r2c2.ai:3000/service", {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
    },
    body: JSON.stringify({
        "service": "custom_action",
        "cmd": {
            "action_name": "lift_control",
            "action_arguments": JSON.stringify({
                "lift_control_socket_ip": "192.168.1.104",
                "lift_control_button_id": 0
            })
        }
    }),
})
.then((response) => response.json())
.then((data) => {
    console.log(data)
})
.catch((error) => {
    console.error(error)
})