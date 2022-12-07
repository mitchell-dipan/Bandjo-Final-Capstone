<template>
  <main>
    <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
    <div id="login">
      <form class="form-signin" @submit.prevent="login">
        <h1>Sign in to Bandjo</h1>
        <div role="alert" v-if="invalidCredentials">
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
          <button id="sign-in" type="submit">Sign in</button>
          <p id="new-account">
            Don't have an account?
            <router-link :to="{ name: 'register' }" id="sign-up-link"
              >Sign up</router-link
            >
          </p>
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
  height: 100vh;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#login {
  display: flex;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 10px;
  padding-left: 10%;
  padding-right: 10%;
  padding-bottom: 5%;
  padding-top: 5%;
  background-color: white;
}
.form-signin {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
h1 {
  text-align: center;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
#sign-in {
  padding: 0.75rem 1.25rem;
  border-radius: 10rem;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: 0.15rem;
  transition: all 0.3s;
  overflow: hidden;
  z-index: 1;
  background-color: #ef8354;
}
#sign-in:hover {
  background-color: #f36b31;
}
#new-account {
  margin-top: 10%;
}
#sign-up-link {
  text-decoration: none;
  color: #ef8354;
}
input {
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  text-align: center;
  padding: 5%;
  margin-top: 10%;
  background-color: white;
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
  width: 20%;
  align-self: center;
}
</style>