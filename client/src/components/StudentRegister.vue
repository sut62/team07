<template>
 <v-app >

   <v-app-bar  color="cyan lighten-7" dark >
    <v-flex mb-3 ="center">
       <h1  class="display-2 font-weight-bold mb-0">CreateAccount</h1>
      </v-flex>
    </v-app-bar>
   <v-content class="cyan lighten-5">
     <v-layout text-center wrap>

      
    </v-layout>

  <v-container class="fill-height"> 
        
                 
    <v-row justify="center">
      <v-col cols="5">
        <v-form >

           <!-- เลขประจำตัวนักศึกษา -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="เลขประจำตัวนักศึกษา"
                v-model="student_id"
                :rules="[(v) => !!v || 'กรุณาใส่เลขประจำตัวนักศึกษา']"
                required
                clearable
                
              ></v-text-field>
            </v-col>
          </v-row>
            
            
          <v-row justify="center">
            <!-- คำนำหน้า -->
            <v-col cols="3">
              <v-select
                label="คำนำหน้า"
                solo
                v-model="student.prefixs"
                :items="prefixs"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาใส่เลือก']"
                required
              ></v-select>
            </v-col>
            <!-- ชื่อ-นามสกุล -->
            <v-col cols="7">
              <v-text-field
                solo
                label="ชื่อ-นามสกุล"
                v-model="student_name"
                :rules="[(v) => !!v || 'กรุณาใส่ ชื่อ-นามสกุล']"
                required
                clearable
                prepend-icon="mdi-account"
              ></v-text-field>
            </v-col>    
          </v-row>

            <!-- Major --> 
          <v-row justify="center">
            <v-col cols="4">
              <v-select
                label="สาขาวิชา"
                solo
                v-model="student.majors"
                :items="majors"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาใส่ สาขาวิชา']"
                required
              ></v-select>
            </v-col>
            
            <!-- year -->  
               <v-col cols="4">
              <v-select
                label="ชั้นปีที่ศึกษา"
                solo
                v-model="student.id"
                :items="year"
                item-text="year_name"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาใส่ ชั้นปีที่ศึกษา']"
                required
              ></v-select>
            </v-col>
          </v-row>

           <!-- email -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="Email"
                v-model="student_email"
                :rules="[(v) => !!v || 'กรุณาใส่ Email']"
                required
                clearable
                prepend-icon="mdi-email"
              ></v-text-field>
            </v-col>
          </v-row>

          <!-- phone -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="Phone"
                v-model="student_phone"
                hint="At least 10 characters"
                :rules="[(v) => !!v || 'กรุณาใส่ Numberphone']"
                required
                clearable
                prepend-icon="mdi-phone"
              ></v-text-field>
            </v-col>
          </v-row>


            <!-- password -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="PASSWORD"
                v-model="password"
                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show1 ? 'text' : 'password'"
                hint="At least 8 characters"
                prepend-icon="mdi-lock"
                required
                counter
                clearable
                @click:append="show1 = !show1"
              ></v-text-field>
            </v-col>
          </v-row>
          
        <!-- ยืมยันpassword -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="ยืนยัน PASSWORD"
                v-model="repassword"
                :type="show2 ? 'text' : 'password'"
                :append-icon="show2 ?  'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required,rules.checkpass]"
                hint="At least 8 characters"
                prepend-icon="mdi-lock"
                required
                counter
                clearable
                @click:append="show2 = !show2"
              ></v-text-field>
            </v-col>
          </v-row>
 
          <v-row justify="center">
            <v-col cols="12">
              <v-btn 
                style="margin-left: 20%;"
                @click="saveData">save
              </v-btn>
              <v-btn 
                style="margin-left: 30%;"
                @click="clear">clear
              </v-btn>
            </v-col>
          </v-row>
    
        </v-form>
      </v-col>
    </v-row>
   </v-container>
  </v-content >
  </v-app >
</template>

<script>


export default {
  name: "student",
  data() {
    return {
      student: {
        prefixs: "",
        majors: "",
        id: "",
      },
        valid: false,
        show1: false,
        show2: false,
        student_id : '',
        student_name : '',
        student_email : '',
        student_phone : '',
        password : '',
        repassword : '',
        
        majors : [],
        prefixs : [],
        year : [],
      rules: {
          required: value => !!value || 'This field is required',
          min: v => v.length >= 8 || 'Min 8 characters',
          checkpass: () => this.repassword == this.password || 'Passwords do not match',
      }
      
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล NameTppe ใส่ combobox
    getprefix() {
      this.$http
        .get("/prefixs")
        .then(response => {
          this.prefixs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  // ดึงข้อมูล Major ใส่ combobox
    getMajor() {
      this.$http
        .get("/majors")
        .then(response => {
          this.majors = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
        // ดึงข้อมูล Year ใส่ combobox
    getYear() {
     this.$http
        .get("/year")
        .then(response => {
          this.year = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  
  
    // function เมื่อกดปุ่ม submit
   saveData() {
     console.log(this.student.id)
    {
      this.$http
        .post(
          "/student/" +
            this.student_id+
             "/" +
             this.student.prefixs +
            "/" +
            this.student_name + 
            "/" +
            this.student.majors +
            "/" +
            this.student.id+
            "/" +
            this.student_email +
            "/" +
             this.student_phone +
            "/" +
             this.password ,
           //  this.student
        )
        .then(response => {
          console.log(response);
            this.$router.push("/studentdata");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    }
    
  },
    checkfeild(){
if(    this.student.student_id=='')
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.prefixs=="")
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.student_name=='')
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.majors=="")
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.id=="")
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.student_email=='')
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.student_phone=='')
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.password=='')
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
if(    this.student.repassword=='')
alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
    },
    clear() {
      //this.$refs.form.reset();
        this.student_id = ''; 
        this.student_name = '';
        this.student_email = '';
        this.student_phone = '';
        this.password = '';
        this.repassword = '';
        this.student.prefixs= '';
        this.student.majors = '';
        this.student.id = '';
    },
    refreshList() {
      this.getprefix();
      this.getMajor();
      this.getYear();
      
    }
    /* eslint-enable no-console */
  },
    mounted() {
      this.getprefix();
      this.getMajor();
      this.getYear();
  }
};
</script>
