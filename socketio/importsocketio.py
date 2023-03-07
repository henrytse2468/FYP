from time import sleep
import socketio

io_client = socketio.Client()

@io_client.event
def message(data):
    print('i have a message!')

@io_client.on("hi")
def on_message(data):
    print(f'i have a message. Which is {data}')

@io_client.on("first response")
def on_message(data):
    print(f'first response is {data}')
    sleep(1)
    io_client.sleep(2)
    io_client.emit("point", "first")
    sleep(2)

@io_client.on("second response")
def on_message(data):
    print(f'second response is {data}')
    sleep(1)
    io_client.sleep(2)
    io_client.emit("point", "second")
    sleep(2)
    io_client.disconnect()


io_client.connect('http://localhost:3000')
print('connected')

io_client.emit("location", '2')

sleep(5)

# io_client.disconnect()