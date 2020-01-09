<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex xs12 sm8 md4>
        <v-card class="elevation-12">
          <v-toolbar color="#26C6DA" dark flat>
            <v-toolbar-title>LOGIN REG</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-card-text>
            <v-form>
              <v-text-field
                v-model="$v.loginForm.username.$model"
                label="Login"
                name="login"
                type="text"
                :error-messages="loginError"
              ></v-text-field>

              <v-text-field
                v-model="$v.loginForm.password.$model"
                label="Password"
                name="password"
                type="password"
                :error-messages="passwordError"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
               <v-btn link to="/Student/" color="primary">Sign Up</v-btn>
            <v-spacer></v-spacer>
            <v-btn @click="login" color="primary">Sign In</v-btn>
          </v-card-actions>
        </v-card>
        <v-snackbar v-model="snackbar">{{ message }}</v-snackbar>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { required, minLength } from "vuelidate/lib/validators";

export default {
  data: () => ({
    loginForm: {
      username: null,
      password: null
    },
    snackbar: false,
    message: null
  }),
  validations: {
    loginForm: {
      username: {
        required
      },
      password: {
        required,
        minLength: minLength(8)
      }
    }
  },
  computed: {
    loginError() {
      const errors = [];
      if (!this.$v.loginForm.username.$dirty) return errors;
      !this.$v.loginForm.username.required && errors.push("กรุณากรอก username");
      return errors;
    },
    passwordError() {
      const errors = [];
      if (!this.$v.loginForm.password.$dirty) return errors;
      !this.$v.loginForm.password.required && errors.push("กรุณากรอก password");
      !this.$v.loginForm.password.minLength &&
        errors.push("password มีความยาวอย่างน้อย 8 ตัวอักษร");
      return errors;
    }
  },
  methods: {
    login: async function() {
      if (!this.$v.loginForm.$invalid) {
        await this.$http
          .post(`auth`, this.$v.loginForm.$model)
          .then(response => {
            if (
              response.data.username !== null &&
              response.data.role !== null
            ) {
              localStorage.setItem("username", response.data.username);
              this.$store.commit("setUsername", response.data.username);
              if (response.data.role === "RegistrationOfficer")
                this.$router.push("/registration");
              if (response.data.role === "Lecturer") this.$router.push("/teachers");
              if (response.data.role === "Student") this.$router.push("/RegisterStudent");
            } else {
              this.message = "username หรือ password ไม่ถูกต้อง";
              this.snackbar = !this.snackbar;
            }
          });
      }
    }
  }
};
</script>
