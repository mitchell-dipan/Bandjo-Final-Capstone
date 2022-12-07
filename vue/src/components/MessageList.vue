<template>
  <main>
    <div class="timeline-box">
      <img id="notes-logo" src="../../assets/notes-logo.png" />
      <div id="message-box" v-for="message in messages" :key="message.id">
        <div class="top-row">
          <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
          <h1>{{ message.name }}</h1>
          <p>Posted on {{ message.messageDate }}</p>
        </div>
        <p id="message">{{ message.messageContent }}</p>
      </div>
    </div>
  </main>
</template>

<script>
import MessageService from "../services/MessageService.js";

export default {
  data() {
    return {
      messages: [],
      // errorMsg: "",
    };
  },
  created() {
    MessageService.getAllNotifications().then((response) => {
      this.messages = response.data;
    });
    // .catch((err) => {
    //   if (err.response === 201 || err.response === 200) {
    //     this.errorMsg = "";
    //   } else {
    //     this.errorMsg = err.response;
    //   }
    // });
  },
};
</script>
<style scoped>
main {
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
  margin-left: 20%;
  width: 70%;
  border-left: #2d3142 solid 2px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.timeline-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 5%;
  width: 70%;
}
#message-box {
  display: flex;
  flex-direction: column;
  border: solid 1px #ef8354;
  width: 100%;
  margin-top: 3%;
  padding-bottom: 5%;
  padding-top: 2%;
  background-color: #2d3142;
  border-radius: 10px;
}
#message-box:hover {
  background-color: #3b4057;
}
.top-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 3%;
}
.top-row img {
  width: 10%;
  border: 1px solid black;
  border-radius: 100%;
  margin-left: 2%;
}
.top-row h1 {
  margin-right: 35%;
  color: white;
}
.top-row p {
  margin-right: 1%;
  color: white;
}
#message {
  padding-left: 3%;
  color: white;
}

#notes-logo {
  align-self: center;
  width: 20%;
}
</style>