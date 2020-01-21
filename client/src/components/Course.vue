<template>
 


   <v-content class="cyan accent-1">
     
     <v-container>
        <v-row justify="center">
           <v-col cols="5">

           <!-- รหัสรายวิชา -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="เลขรหัสรายวิชา"
                v-model="courses.courseCode"
                :rules="[(v) => !!v || 'กรุณาใส่เลขรหัสรายวิชา']"
                required
                clearable
                
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center">

            <!-- หน่วยกิจ -->
            <v-col cols="5">
              <v-text-field
                solo
                label="เลขหน่วยกิจ"
                v-model="courses.credit"
                :rules="[(v) => !!v || 'กรุณาใส่เลขหน่วยกิจ']"
                required
                clearable
              ></v-text-field>
            </v-col>

            <!-- ชื่อวิชา -->
            <v-col cols="7">
              <v-text-field
                solo
                label="ชื่อรายวิชา"
                v-model="courses.name"
                :rules="[(v) => !!v || 'กรุณาใส่ ชื่อรายวิชา']"
                required
                clearable
              ></v-text-field>
            </v-col>    
          </v-row>

          <!-- หลักสูตร -->
          <v-row justify="center">
            <v-col cols="10">
              <v-select
                label="หลักสูตร"
                solo
                v-model="courses.programInfo_id"
                :items="programInfos"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาเลือกหลักสูตร']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <!-- ภาคเรียน -->
          <v-row justify="center">
            <v-col cols="10">
              <v-select
                label="ภาคเรียนศึกษา"
                solo
                v-model="courses.trimester_id"
                :items="trimesters"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาเลือกภาคเรียนศึกษา']"
                required
              ></v-select>
            </v-col>
          </v-row>

         <!-- ประเภท -->
          <v-row justify="center">
            <v-col cols="10">
              <v-select
                label="ประเภทวิชาที่เปิดสอน"
                solo
                v-model="courses.type_id"
                :items="types"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาเลือกประเภทวิชาที่เปิดสอน']"
                required
              ></v-select>
            </v-col>
          </v-row>

        <v-row justify="center">
                <v-col cols="auto">
                  <v-btn dark large @click="saveCourse" class="indigo">ยืนยัน</v-btn>
                  <v-snackbar v-model="snackbar">{{text}}</v-snackbar>
                 </v-col>
                 
                </v-row>
          
  </v-col>
  </v-row>
   </v-container>
  </v-content >
  
</template>

<script>

export default {
  name: "course",
  data() {
    return {
      courses: {
        personalId: "",
        courseCode: "",
        credit: "",
        name: "",
        programInfoId:"",
        trimesterId:"",
        typeId:"",
      },

      personals:[],
      courseCode:[],
      credit:[],
      name:[],
      programInfos:[],
      trimesters:[],
      types:[],
      valid: false,
      snackbar: false,
    };
  },
  methods: {
    getProgramInfos() {
      this.$http
        .get("/programInfo")
        .then(response => {
          this.programInfos = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getLecturers() {
      this.$http
        .get("/lecturer")
        .then(response => {
          this.lecturers = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getTrimesters() {
      this.$http
        .get("/trimester")
        .then(response => {
          this.trimesters = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
      getTypes() {
      this.$http
        .get("/type")
        .then(response => {
          this.types = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


    saveCourse() {
      this.$http
        .post(
          "/courses/course/" +
            this.courses.programInfo_id
            + "/"  +
            this.courses.personalId
            + "/"  +
            this.courses.courseCode
            + "/" +
            this.courses.credit
            + "/" +
            this.courses.name
            + "/" +
            this.courses.trimester_id
            + "/" +
            this.courses.type_id,
            this.courses
        )
        .then(response => {
                  console.log(response);
                  this.text = 'บันทึกข้อมูลสำเร็จ';
                  this.snackbar = true;
                  this.$refs.form.reset();
                    })
                    .catch(e => {
                        console.log(e);
                        this.text = 'บันทึกข้อมูลไม่สำเร็จ';
                        this.snackbar = true;
                    });
            },
    refreshList() {
    this.getProgramInfos();
    this.getTrimesters();
    this.getTypes();
    },
  },
  mounted() {
    this.getProgramInfos();
    this.getTrimesters();
    this.getTypes();
 
  },
  created(){
    this.courses.personalId = this.$store.state.username
  }
}
</script>

  