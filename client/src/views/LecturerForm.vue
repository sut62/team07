<template>
  <v-card class="mx-auto cyan lighten-4" raised width="50%">
    <v-card-title>เพิ่มอาจารย์ผู้สอน</v-card-title>
    <v-card-text>
      <v-form>
        <v-select
          :items="prefixs"
          item-text="name"
          label="คำนำหน้า"
          item-value="id"
          v-model="$v.lecturerForm.prefix.$model"
          :error-messages="prefixError"
        ></v-select>
        <v-text-field
          label="ชื่อ - สกุล"
          v-model="$v.lecturerForm.name.$model"
          :error-messages="nameError"
        ></v-text-field>
        <v-text-field
          label="รหัสอาจารย์"
          v-model="$v.lecturerForm.lecturerCode.$model"
          :error-messages="lecturerCodeError"
        ></v-text-field>
        <v-text-field
          label="รหัสผ่าน"
          type="password"
          v-model="$v.lecturerForm.password.$model"
          :error-messages="passwordError"
        ></v-text-field>
        <v-radio-group
          label="เพศ"
          row
          v-model="$v.lecturerForm.gender.$model"
          :error-messages="genderError"
        >
          <v-radio
            v-for="gender in genders"
            :key="gender.id"
            :value="gender.id"
            :label="gender.name"
            color="primary"
          ></v-radio>
        </v-radio-group>
        <v-text-field
          label="เลขบัตรประชาชน"
          maxlength="13"
          v-model="$v.lecturerForm.personalId.$model"
          :error-messages="personalIdError"
        ></v-text-field>
        <v-text-field
          label="เบอร์โทรศัพท์"
          maxlength="10"
          type="tel"
          v-model="$v.lecturerForm.tel.$model"
          :error-messages="telError"
        ></v-text-field>
        <v-text-field
          label="E-mail"
          type="email"
          v-model="$v.lecturerForm.email.$model"
          :error-messages="emailError"
        ></v-text-field>
        <v-combobox
          label="สำนักวิชา"
          :items="institutes"
          item-text="name"
          item-value="id"
          @change="setSpecificMajor($v.institute.$model.id)"
          v-model="$v.institute.$model"
          :error-messages="instituteError"
        ></v-combobox>
        <v-combobox
          label="สาขาวิชา"
          :items="specificMajor"
          item-text="name"
          item-value="id"
          v-model="$v.lecturerForm.major.$model"
          :error-messages="majorError"
        ></v-combobox>
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="primary"
        text
        @click="createLecturer"
        :disabled="$v.lecturerForm.$invalid"
      >เพิ่มอาจารย์ผู้สอน</v-btn>
    </v-card-actions>
    <v-snackbar v-model="snackbar">{{ message }}</v-snackbar>
  </v-card>
</template>

<script>
import { required, minLength, numeric, email } from "vuelidate/lib/validators";
import { mapState, mapMutations } from "vuex";

export default {
  data: () => ({
    table: false,
    snackbar: false,
    message: "",
    institute: null,
    lecturerForm: {
      prefix: null,
      name: null,
      lecturerCode: null,
      password: null,
      gender: null,
      personalId: null,
      tel: null,
      email: null,
      major: null,
      createdBy: null
    }
  }),
  validations: {
    institute: {
      required
    },
    lecturerForm: {
      prefix: {
        required
      },
      name: {
        required
      },
      lecturerCode: {
        required
      },
      password: {
        required,
        minLength: minLength(8)
      },
      gender: {
        required
      },
      personalId: {
        required,
        numeric
      },
      tel: {
        required,
        numeric
      },
      email: {
        email,
        required
      },
      major: {
        required
      },
      createdBy: {
        required
      }
    }
  },
  created() {
    this.$store.commit("setGenders");
    this.$store.commit("setInstitutes");
    this.$store.commit("setPrefixs");
    this.$v.lecturerForm.createdBy.$model = this.$store.state.username;
  },
  computed: {
    ...mapState({
      prefixs: state => state.prefixs,
      institutes: state => state.institutes,
      genders: state => state.genders,
      specificMajor: state => state.specificMajor
    }),
    prefixError() {
      const errors = [];
      if (!this.$v.lecturerForm.prefix.$dirty) return errors;
      !this.$v.lecturerForm.prefix.required &&
        errors.push("กรุณาเลือกคำนำหน้า");
      return errors;
    },
    nameError() {
      const errors = [];
      if (!this.$v.lecturerForm.name.$dirty) return errors;
      !this.$v.lecturerForm.name.required &&
        errors.push("กรุณากรอกชื่อ - สกุล");
      return errors;
    },
    lecturerCodeError() {
      const errors = [];
      if (!this.$v.lecturerForm.lecturerCode.$dirty) return errors;
      !this.$v.lecturerForm.lecturerCode.required &&
        errors.push("กรุณากรอกรหัสอาจารย์");
      return errors;
    },
    passwordError() {
      const errors = [];
      if (!this.$v.lecturerForm.password.$dirty) return errors;
      !this.$v.lecturerForm.password.required &&
        errors.push("กรุณากรอกรหัสผ่าน");
      !this.$v.lecturerForm.password.minLength &&
        errors.push("รหัสผ่านความยาวอย่างน้อย 8 ตัวอักษร");
      return errors;
    },
    genderError() {
      const errors = [];
      if (!this.$v.lecturerForm.gender.$dirty) return errors;
      !this.$v.lecturerForm.gender.required && errors.push("กรุณาเลือกเพศ");
      return errors;
    },
    personalIdError() {
      const errors = [];
      if (!this.$v.lecturerForm.personalId.$dirty) return errors;
      !this.$v.lecturerForm.personalId.required &&
        errors.push("กรุณากรอกรหัสบัตรประชาชน");
      !this.$v.lecturerForm.personalId.numeric &&
        errors.push("รหัสบัตรประชาชนควรเป็นตัวเลขเท่านั้น");
      return errors;
    },
    telError() {
      const errors = [];
      if (!this.$v.lecturerForm.tel.$dirty) return errors;
      !this.$v.lecturerForm.tel.required &&
        errors.push("กรุณากรอกเบอร์โทรศัพท์");
      !this.$v.lecturerForm.tel.numeric &&
        errors.push("เบอร์โทรศัพท์ควรเป็นตัวเลขเท่านั้น");
      return errors;
    },
    emailError() {
      const errors = [];
      if (!this.$v.lecturerForm.email.$dirty) return errors;
      !this.$v.lecturerForm.email.required && errors.push("กรุณากรอก email");
      !this.$v.lecturerForm.email.email &&
        errors.push("รูปแบบ email ไม่ถูกต้อง");
      return errors;
    },
    majorError() {
      const errors = [];
      if (!this.$v.lecturerForm.major.$dirty) return errors;
      !this.$v.lecturerForm.major.required && errors.push("กรุณาเลือกสาขาวิชา");
      return errors;
    },
    instituteError() {
      const errors = [];
      if (!this.$v.institute.$dirty) return errors;
      !this.$v.institute.required && errors.push("กรุณาเลือกสำนักวิชา");
      return errors;
    }
  },
  methods: {
    ...mapMutations(["setSpecificMajor"]),
    createLecturer: async function() {
      this.$v.lecturerForm.major.$model = this.$v.lecturerForm.major.$model.id;
      try {
        await this.$store.dispatch(
          "createLecturer",
          this.$v.lecturerForm.$model
        );
        this.message = "เพิ่มอาจารย์ผู้สอนสำเร็จ";
        this.reset();
      } catch (error) {
        this.message = "ไม่สามารถเพิ่มอาจารย์ผู้สอนได้";
        this.reset();
      } finally {
        this.snackbar = true;
      }
    },
    reset: async function() {
      this.$v.lecturerForm.$model = {
        prefix: null,
        name: null,
        lecturerCode: null,
        password: null,
        gender: null,
        personalId: null,
        tel: null,
        email: null,
        major: null,
        createdBy: this.$store.state.username
      };
      this.$v.institute.$model = null;
    }
  }
};
</script>