function Synapse(url,receiveCallback) {

  if(!url){
    console.log("url is not found")
  } else {
    this.url = url
  }

  if ('WebSocket' in window) {
    this.websocket = new WebSocket(url)
  } else if ('MozWebSocket' in window) {
    this.websocket = new MozWebSocket(url)
  } else {
    console.log("WebSocket not supported")
  }

  var receive = receiveCallback;
  this.websocket.onmessage = function(event){
    receive(event.data);
  }

  this.notify = function(tag,text){
    if (tag) {
    var data = {}
    data.message = text
    data.tag = tag
    data.kind = 'notify'
    this.websocket.send(JSON.stringify(data))
    }else{console.log("tag not found")}
  }

  this.join = function(tag){
    if (tag) {
    var data = {}
    data.tag = tag
    data.kind = 'join'
    this.websocket.send(JSON.stringify(data));
    }else{console.log("tag not found")}
  }

  this.leave = function(tag){
    if (tag) {
    var data = {}
    data.tag = tag
    data.kind = 'leave'
    this.websocket.send(JSON.stringify(data));
    }else{console.log("tag not found")}
  }

  this.quit = function(){
    var code = 4500;
    var reason = "Disconnect";
    this.websocket.close(code,reason);
  }

}