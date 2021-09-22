package com.company;

public class MBRC {
    Message messageBuffer;
    Message response_buffer;
    boolean messageBufferFull;
    boolean responseBufferFull;


//    public send (in message, out response)
//    place message in buffer;
//    messageBufferFull := true;
//    signal;
//    while responseBufferFull = false do wait;
//    remove response from response buffer;
//    responseBufferFull := false;
//    end send;
    public void send(Message message) {
        // place message in buffer
        this.messageBuffer = message;

        // Set messageBufferFull to true
        this.messageBufferFull = true;

        // signal;

        if (this.messageBuffer == null) {
            this.messageBuffer = message;
        }
    }


    /*****************************************************************************************
     *  Retrieves a Message from messageBuffer, clears the buffer, and returns the Message
     *  from the messageBuffer
     * @return Message: the Message that was in messageBuffer
     * @throws Exception
     ******************************************************************************************/
    public Message receive() throws Exception {
        // Wait for messageBufferFull to be true
        while (!this.messageBufferFull) {
            wait(500);
        }
        // Remove message
        Message message = this.messageBuffer;
        this.messageBufferFull = false;
        return message;
    }

//    public reply (in response)
//    Place response in response buffer;
//    responseBufferFull := true;
//    signal;
//    end reply;
    public void reply(Message message) {

    }
}
