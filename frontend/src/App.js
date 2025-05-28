import React, { useEffect, useState } from 'react';
import axios from 'axios';

//今まで送ったメッセージ一覧
const[chats,SetChats] = useState([]);
//入力欄、入力更新のメッセージ
const[newChats, SetNewChats] = useState("");

//API呼び出し
useEffect(() => {
  axios.get('http://localhost:8080/api/chats')
    .then(res => setChats(res.data))
    .catch(err => console.error(err));
}, []);


const handleSend = () => {
  //空なら送らない
  if (!newMessage.trim()) return;

  axios.get('http://localhost:8080/api/chats', {
    content: NewChats 
  }).then(res => {
    setChats([...messages, res.data]);// 新しいメッセージをリストに追加
    setChats('');
  }).catch(err => console.error(err));
}

return(
  <div style={{ padding: "20px" }}>

    <h2>Smart-chat</h2>

    /** 全メッセージを1つずつ画面に表示 */ 
    <div>
      {chats.map((msg,index) =>  (
        <div key={index}>{msg.content}</div>
      ))}
    </div>

    <input 
      value = {newChat}
      onChange = {e => setChat(e.target.value)}
      placeholder="Type a message..."
    />

    <button onClick={handleSend}>Send</button>
  </div>

)

export default App;