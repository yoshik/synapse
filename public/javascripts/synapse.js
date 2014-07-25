function Synapse(receiveCallback) {

  const url = "ws://localhost:9000/synapse";

  if(!url){
    console.log("url is not found");
  } else {
    this.url = url;
  }

  if ('WebSocket' in window) {
    this.websocket = new WebSocket(url);
  } else if ('MozWebSocket' in window) {
    this.websocket = new MozWebSocket(url);
  } else {
    console.log("WebSocket not supported");
  }

  this.websocket.onopen = function () {
    SynapseStart();
  }

  var receive = receiveCallback;
  this.websocket.onmessage = function(event){
    console.log(event.data);
    var result = JSON.parse(event.data);
    receive(result);
  }

  this.notify = function(tag,send_data){
    if (tag) {
      var data = {}
      data.message = JSON.stringify(send_data);
      data.tag = tag;
      data.kind = 'notify';
      this.websocket.send(JSON.stringify(data));
    }else{console.log("tag not found");}
  }

  this.join = function(tag){
    if (tag) {
      var data = {};
      data.tag = tag;
      data.kind = 'join';
      this.websocket.send(JSON.stringify(data));
    }else{console.log("tag not found");}
  }

  this.leave = function(tag){
    if (tag) {
      var data = {};
      data.tag = tag;
      data.kind = 'leave';
      this.websocket.send(JSON.stringify(data));
    }else{console.log("tag not found");}
  }

  this.quit = function(){
    var code = 4500;
    var reason = "Disconnect";
    this.websocket.close(code,reason);
  }

}