// ES6 import or TypeScript
// import { io } from "socket.io-client";
// CommonJS
const io = require("socket.io-client");

const socket = io("http://localhost:3000");

socket.on("connect", () => {
  console.log('connecting...');
  
  if(socket.connected){
    console.log('connected. ID is ' + socket.id);
    socket.emit("location", "2");
    // socket.disconnect();
  }
});

socket.once("first response", (response)=>{
  console.log('first response is '+response);
  socket.emit("point", "first");
});

socket.once("second response", (response)=>{
  console.log('second response is '+response);
  socket.emit("point", "second");
  socket.disconnect();
});

