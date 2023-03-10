// ES6 import or TypeScript
// import { io } from "socket.io-client";
// CommonJS

var room_num;
var floor;

room_num = process.argv[2];
floor = process.argv[3];

// function sleep(ms) {
//   return new Promise(resolve => setTimeout(resolve, ms));
// }

const io = require("socket.io-client");

const socket = io("http://localhost:3000");

socket.on("connect", () => {
  console.log('connecting...');
  
  if(socket.connected){
    console.log('connected. ID is ' + socket.id);
    socket.emit("location", room_num);
    
  }
});

socket.once('location_ok', (response)=>{
  console.log(response);
  socket.emit('action', 'evelator');
});

socket.on("response",(args)=>{
    if (args['stage']=='evelator') {
      console.log(args.stage + ' is ' + args.status);
      socket.emit("action", "toFloor");

    } else if(args['stage']=='floorX') {
        console.log(args.stage + ' is ' + args.status);
        socket.emit('action', 'toRoomX');
    }
});

socket.once('finished', (response)=>{
  console.log(response + ', now disconnecting...');
  socket.disconnect();
});
