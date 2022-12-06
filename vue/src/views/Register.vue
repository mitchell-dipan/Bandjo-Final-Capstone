<template>
  <main>
    <img src="../../assets/bandjo-logo-final-removebg-preview.png" />
    <div id="register">
      <form class="form-register" @submit.prevent="register">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
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
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
        <div class="buttons">
          <router-link :to="{ name: 'login' }" id="button-style"
            >Have an account?</router-link
          >
          <button type="submit" id="button-style">Create Account</button>
        </div>
      </form>
    </div>
  </main>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
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
#register {
  display: flex;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 10px;
  padding-left: 10%;
  padding-right: 10%;
  padding-bottom: 10%;
}
form {
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
.buttons {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin-top: 10%;
}
#button-style {
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
img {
  width: 15%;
  align-self: center;
}
</style>
