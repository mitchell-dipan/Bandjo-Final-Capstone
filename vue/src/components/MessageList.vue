<template>
  <main>
    <div class="timeline-box">
      <img id="notes-logo" src="../../assets/notes-logo.png" />
      <div>
        <!-- <select name="sort" id="sort">
          <option value="Time" @click="sortByTime">Most Recent</option>
          <option value="Alphabetically" @click="sortByBand">By Band</option>
        </select> -->
        <div v-if="sort">
          <button @click="sortByBand">Sort By Band</button>
        </div>
        <div v-else>
          <button @click="sortByTime">Sort By Date Received</button>
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
    MessageService.getAllNotifications(this.band).then((response) => {
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

#link {
  text-decoration: none;
}
</style>