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
          <button type="submit" id="button-style">Create Account</button>
          <p id="have-account">
            Have an account?
            <router-link :to="{ name: 'login' }" id="login-link"
              >Login</router-link
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
  height: 100vh;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#register {
  display: flex;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 10px;
  padding-left: 8%;
  padding-right: 8%;
  padding-bottom: 3%;
  padding-top: 3%;
  background-color: white;
}
.form-register {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
h1 {
  text-align: center;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
input {
  margin-top: 10%;
  background-color: white;
}
.buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10%;
}
#have-account {
  margin-top: 10%;
}
#login-link {
  text-decoration: none;
  color: #ef8354;
}
#button-style {
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
#button-style:hover {
  background-color: #f36b31;
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
img {
  width: 20%;
  align-self: center;
}
</style>
