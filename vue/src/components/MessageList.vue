<template>
  <main>
    <div class="timeline-box">
      <div id="logo-and-button">
        <img id="notes-logo" src="../../assets/notes-logo.png" />
        <div class="sort-by-button">
          <div v-if="sort">
            <button @click="sortByBand">Sort By Band</button>
          </div>
          <div v-else>
            <button @click="sortByTime">Sort By Date</button>
          </div>
        </div>
      </div>
      <div id="message-box" v-for="message in messages" :key="message.id">
        <router-link
          id="link"
          :to="{ name: 'page', params: { id: message.bandId } }"
        >
          <div class="top-row">
            <img v-bind:src="message.profilePic" />
            <h1>{{ message.name }}</h1>
            <p>Posted on {{ message.messageDate }}</p>
          </div>
          <p id="message">{{ message.messageContent }}</p>
        </router-link>
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
      time: "time",
      band: "band",
      sort: true,
    };
  },
  created() {
    MessageService.getAllNotifications(this.time).then((response) => {
      this.messages = response.data;
    });
  },
  methods: {
    sortByTime() {
      this.sort = true;
      MessageService.getAllNotifications(this.time).then((response) => {
        this.messages = response.data;
      });
    },
    sortByBand() {
      this.sort = false;
      MessageService.getAllNotifications(this.band).then((response) => {
        this.messages = response.data;
      });
    },
  },
  computed: {
    displayMessages() {
      let list = this.messages;
      return list;
    },
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
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#logo-and-button {
  display: flex;
  flex-direction: column;
  width: 80%;
}
#notes-logo {
  align-self: center;
  width: 30%;
  margin-bottom: 5%;
}
.sort-by-button button {
  justify-self: flex-start;
  border: None;
  color: #fff;
  text-transform: uppercase;
  font-size: 0.9rem;
  letter-spacing: 0.15rem;
  background: none;
}
.sort-by-button button:hover {
  background-color: #fcfbf4;
  border-radius: 15px;
  border: none;
  color: black;
  transition-delay: 0.2s;
}
.timeline-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 5%;
  width: 60vw;
}
#message-box {
  display: flex;
  flex-direction: column;
  width: 80%;
  margin-top: 3%;
  padding-bottom: 5%;
  padding-top: 2%;
  background-color: #fcfbf4;
  border-radius: 10px;
}
#message-box:hover {
  background-color: #eeede5;
}
.top-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 3%;
}
.top-row img {
  width: 3vw;
  height: 6vh;
  border-radius: 50%;
  object-fit: cover;
  object-position: center;
  margin-left: 2%;
}
.top-row h1 {
  margin-right: 35%;
  color: black;
}
.top-row p {
  margin-right: 1%;
  color: black;
}
#message {
  padding-left: 3%;
  color: black;
}

#link {
  text-decoration: none;
}
</style>