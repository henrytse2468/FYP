# Lift Control

## 1. Background

The lift control panel is controlled by the ESP32 via Socket calling

## 2. Background

The ESP32 will to set to connect to the ARC wifi

The ESP32 socket IP will be something like `192.168.1.100-110` (Depends on the setting) and the port will be `999` for all of the lift control system.

## 3. How to control?

The following example will be using the `socket.js`. You should be able to use your own program to call the ESP32.

### 3.1. Run the example script

```bash
node socket.js
```

The script will send post request to the ARC, and the ARC will create a socket to communicate with the ESP32.

#### 3.1.1. Json parameters

```javascript
{
    "service": "custom_action",
    "cmd": {
        "action_name": "lift_control",
        "action_arguments": JSON.stringify({
            "lift_control_socket_ip": "192.168.1.100",
            "lift_control_button_id": 0
        })
    }
}
```

## 4. Data

### 4.1. Example of response

***Success:***

```javascript
{
    "action_result_code": 0
    "action_result_message": "Lift arrived"
}
```

***Failed:***

```javascript
{
    "action_result_code": 1
    "action_result_message": "Reach max retry count"
}
```

or

```javascript
{
    "action_result_code": 1
    "action_result_message": "Deserialization error" / "Unknown servo id" / "Wait lift timeout"
}
```

> If the `action_result_message` shows `Deserialization error`, it means that request data format is not correct \
> If the `action_result_message` shows `Unknown servo id`, it means that the `lift_button_id` is not within the range (0 to Servo number - 1) \
> If the `action_result_message` shows `Reach max retry count`, it means that the lift control system had tried to press the button, but it is not sure is the button is pressed or not (It is a hardware problem)
> If the `action_result_message` shows `Wait lift timeout`, it means that the lift doesn't arrive with in the time interval (Currently is 2 mins)

See below section for more message

#### 4.1.1. Json parameters

**action_result_code** (int): 0 for success / 1 for failed action
**action_result_message** (string): Message of the result

> If `action_result_code` is `-3` / `-4`, please check the `POST` request \
> For other negative value, please contact us
