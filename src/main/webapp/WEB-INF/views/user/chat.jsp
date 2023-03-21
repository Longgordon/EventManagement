<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#chatBtn {
position: fixed;
  bottom: 0px;
  right: 0px;
  background-color: green;
  color: white;
  border: none;
  padding: 15px;
  border-radius: 50%;
  cursor: pointer;
}

#chatBox {
  display: none;
  position: fixed;
  bottom: 40px;
  right: 20px;
  width: 300px;
  border: 1px solid green;
  border-radius: 10px;
}

.chat-header {
  background-color: green;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-radius: 10px 10px 0 0;
}

.chat-header h3 {
  margin: 0;
}

#closeBtn {
  background-color: transparent;
  border: none;
  color: white;
  font-size: 20px;
  cursor: pointer;
}

.chat-body {
background-color: rgb(205 255 127 / 54%);
  height: 200px;
  overflow-y: scroll;
  padding: 10px;
}

.chat-footer {
background-color: #607D8B;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-top: 1px solid green;
}

.chat-footer input[type="text"] {
  flex: 1;
  padding: 5px;
  border: none;
  border-radius: 5px;
  margin-right: 10px;
}

.chat-footer button {
  background-color: green;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}

</style>
</head>
<body>
<button id="chatBtn">
  <i class="fa fa-envelope"></i>
</button>

<div id="chatBox">
  <div class="chat-header">
    <h3>Chat với chúng tôi</h3>
    <button id="closeBtn">
      <i class="fa fa-times"></i>
    </button>
  </div>
  <div class="chat-body">
    <!--Khung chat sẽ được thêm vào đây-->
  </div>
  <div class="chat-footer">
    <input type="text" placeholder="Nhập tin nhắn...">
    <button>Gửi</button>
  </div>
</div>

</body>
<script>
const chatBtn = document.getElementById('chatBtn');
const chatBox = document.getElementById('chatBox');
const closeBtn = document.getElementById('closeBtn');

chatBtn.addEventListener('click', () => {
  chatBox.style.display = chatBox.style.display === 'none' ? 'block' : 'none';
});

closeBtn.addEventListener('click', () => {
  chatBox.style.display = 'none';
});

</script>
</html>