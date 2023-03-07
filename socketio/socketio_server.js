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
    socket.emit("first response", "recieved");
  });

  socket.on("point", (arg)=>{
    console.log('the '+arg+' point is arrived.');
    switch (arg) {
        case 'first':
            socket.emit("second response", "recieved");
            break;
        case 'second':
            // socket.emit("second response", "recieved");
            break;
    
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