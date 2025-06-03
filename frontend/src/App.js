import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  //今まで送ったメッセージ一覧
  const[chats,setChats] = useState([]);
  //入力欄、入力更新のメッセージ
  const[newChat, setNewChat] = useState("");

  //API呼び出し
  useEffect(() => {
    axios.get('http://localhost:8080/api/chats')
      .then(res => setChats(res.data))
      .catch(err => console.error(err));
  }, []);


  const handleSend = () => {
    //空なら送らない
    if (!newChat.trim()) return;

    axios.post('http://localhost:8080/api/chats', {
      text: newChat,
      name:"Taho",
    }).then(res => {
      setChats([...chats, res.data]); // 新しいメッセージを追加
      setNewChat(''); // 入力欄をリセット
    }).catch(err => console.error(err));
  };

  return(
    <div style={{ padding: "20px" }}>

      <h2>Smart-chat</h2>
      <div>
        {chats.map((msg,index) =>  (
          <div key={index}>{msg.text}</div>
        ))}
      </div>

      <input 
        value = {newChat}
        onChange = {e => setNewChat(e.target.value)}
        placeholder="Type a message..."
      />

      <button onClick={handleSend}>Send</button>
    </div>

  )
}

export default App;