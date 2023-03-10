

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}


const httpServer = require("http").createServer();
const io = require("socket.io")(httpServer, {
  // ...
});

io.on("connection", (socket) => {
  // ...
  console.log("someone connected");
  console.log('connect id is ' + socket.id);
  socket.emit("hello", "world");

  socket.on("hi", (arg)=>{
    console.log(arg);
  });

  socket.on("location", (arg)=>{
    console.log('the location is in ' + arg + ' floor');
    socket.emit("location_ok", "location is recieved");
  });

  socket.on("action", async (arg)=>{
    switch (arg) {
        case 'evelator':
            await sleep(5000);
            console.log('arrived evelator');
            socket.emit("response", {"stage":'evelator', 'status':'arrived'});
            break;
        case 'toFloor':
          console.log('going to floorX...')
          await sleep(5000);
          socket.emit("response", {"stage":'floorX', 'status':'arrived'});
          // socket.emit('fuck', '');
          console.log('emitted');
            // socket.emit("second response", "recieved");
            break;
        case 'toRoomX':
          console.log('going to roomX');
          await sleep(4000);
          socket.emit('finished', 'all finished')
        default:
            break;
    }
    // socket.emit("first response", "recieved");
  });

  socket.on('disconnect', ()=>{
    console.log('someone disconnected')
  });
});

io.on("hi", (socket) => {
    // ...
    console.log()
  });

httpServer.listen(3000, ()=>{
    console.log("running...")
});
