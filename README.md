## REST endpoints

| Method / PATH                                     | Description      |
|---------------------------------------------------|------------------|
| GET http://localhost:9090/channels                | Get all channels |
| POST http://localhost:9090/channels               | Create a channel |
| DELETE http://localhost:9090/channels/{channelId} | Remove a channel |


## Websocket endpoints

| PATH                                     | Description      |
|------------------------------------------|------------------|
| ws://localhost:9090/sub/channels         | Permanent Channel|
| ws://localhost:9090/sub/chat/{channelId} | Chat             |

## Websocket Http Handshake Headers
If header `userId` is present, it'll be used as display name in the chats fro that user. Otherwise, an auto incremental `user-1 user-2` ... will be assigned to sessions without such header.

## Logics
- A list of available channels is sent to the session once the user connects (Permanent Chat Only)
- Chat history is shown once a user connects to a channel (All Kind of Chats)
- Users can send and see messages in the same Websocket connection to a chat

## Notifications
- Channel Created (Permanent Chat Only)
- User Joined Chat
- New Message From Users

