<template>
  <main>
    <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
    <div id="login">
      <form class="form-signin" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">Login</h1>
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username or password!
        </div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
        </div>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <div class="buttons">
          <router-link :to="{ name: 'register' }" id="new-account"
            >Register</router-link
          >
          <button id="new-account" type="submit">Sign in</button>
        </div>
      </form>
    </div>
  </main>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>
main {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  height: 90vh;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#login {
  display: flex;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 10px;
  padding-left: 10%;
  padding-right: 10%;
  padding-bottom: 10%;
}
.form-signin {
  display: flex;
  flex-direction: column;
}
h1 {
  text-align: center;
}
input,
button {
  margin-top: 10%;
}
#new-account {
  text-decoration: none;
  text-align: center;
  border-radius: 16px;
  background: black;
  color: whitesmoke;
  font-size: 1.5em;
}

input {
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  text-align: center;
  padding: 5%;
}
input::placeholder {
  font-size: 16px;
  text-align: center;
}
.buttons {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin-top: 10%;
}
img {
  width: 15%;
  align-self: center;
}
</style>