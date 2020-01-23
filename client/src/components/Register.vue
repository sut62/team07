<template>
<v-content>
  <v-container>
    
    <v-layout text-center wrap>
      
      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">
          ระบบลงทะเบียนเรียน
        </h1>
        
        <!-- semester -->
        <v-form v-model="validsec" ref="form">
          <v-row justify="center">
            <v-col cols="auto">
              <v-select
                label="ภาคการศึกษา"
                outlined
                v-model="register.semesterId"
                :items="semesters"
                item-text="sem"
                item-value="id"
                hide-selected
                :rules="[(v) => !!v || '*กรุณาเลือกภาคการศึกษา']"
                required
                ></v-select>
              </v-col>
            </v-row>

            <!-- student -->
            <v-row justify="center">
              <v-col cols="auto">
                <v-select
                  label="รหัสนักศึกษา"
                  outlined
                  v-model="register.studentId"
                  :items="students"
                  item-text="student_id"
                  item-value="id"
                  hide-selected
                  :rules="[(v) => !!v || '*กรุณาเลือกรหัสนักศึกษา']"
                  required
                  ></v-select>
                </v-col>
              </v-row>

              <subject-list-table @selectedCourses="getCourse"></subject-list-table>
           
              <v-col cols='auto'> 
                <v-btn @click="showSection" 
                :class="{ red: !validsec, green: validsec }">ค้นหากลุ่มเรียน</v-btn>
                </v-col>
              
          </v-form>

            <!-- section -->
          <v-form v-model="validsave" ref="form">
            <div v-if="this.getSectionCheck">
              <v-row justify="center">
                  <v-col cols="auto">
                    <v-card color="#FFCC80">
                    </v-card><br>
                    <v-select
                      label="กลุ่มเรียน"
                      outlined
                      v-model="register.sectionId"
                      :items="sections"
                      item-text="sec"
                      item-value="id"
                      ></v-select>
                    </v-col>
                </v-row>

                   <!-- Note  -->
           <v-row justify="center">
          <v-col cols="12" sm="6" md="3">
          <v-text-field
            label="หมายเหตุ(ภาษาไทย)"
             v-model="register.note"
             required
          ></v-text-field>
        </v-col>
           </v-row>      


              <!-- submit -->
              <v-row justify="center">
                <v-col cols="auto">
                  <v-btn dark large @click="saveRegister" class="indigo">ยืนยันการลงทะเบียน</v-btn>
                 </v-col>
                </v-row>

                <v-row justify="center">
                <v-col cols="auto">
                  <v-btn dark large @click="clear" class="indigo">Clear</v-btn>
                 </v-col>
                </v-row>

              </div>
            </v-form>
            
          
        </v-flex>
      </v-layout>
    </v-container>
</v-content>
</template>

<script>
import SubjectListTable from '@/components/SubjectListTable'

export default {
  name: "register",
  components: {
    SubjectListTable
  },
  data() {
    return {
      register: {
        studentId: "",
        semesterId: "",
        sectionId: "",
        courseId:"",
        courseCode:null,
        credit:null,
        note:""
      
      },
      semesters:[],
      students:[],
      courses:[],
      sections:[],
      validsec: false,
      validsave: false,
      getSectionCheck: false,
      saveCheck: false,
      studentName: "",
      courseName: "",
      note:""
    };
  },
  methods: {
    getSemesters() {
      this.$http
        .get("/semester")
        .then(response => {
          this.semesters = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getStudents() {
      this.$http
        .get("/student")
        .then(response => {
          this.students = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getCourses() {
      this.$http
        .get("/courses")
        .then(response => {
          this.courses = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
       getSubnum() {
      this.$http
        .get("/courses/" + this.register.courseId)
        .then(response => {
          console.log(response.data);
          this.s = response.data;
          this.register.courseCode = response.data.courseCode;
          this.register.credit = response.data.credit;
          this.courseName = response.data.name;
          if(response.data!=null)
            this.saveCheck = response.status;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getSections() {
      console.log(this.register.courseId)
      this.$http
        .get("/section/sec/" + this.register.courseId)
        .then(response => {
          if(response.data!=null)
            this.sections = response.data;
           console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
        this.getSubnum();
    },
    showSection(){
        if(this.register.courseId != null 
          && this.register.semesterId != null 
          && this.register.studentId != null ){
             this.getSectionCheck = true;   
            this.getSections();
         }

        },
       
    saveRegister() {
      this.$http
        .post(
          "/register/" +
            this.register.studentId
            + "/"  +
            this.register.semesterId
            + "/"  +
            this.register.sectionId
            + "/" +
            this.register.courseCode
            + "/" +
            this.register.credit
             + "/" +
            this.register.note
        )
        .then(response => {
          console.log(response);
          // this.$router.push("viewreg");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
     clear(){
        this.register = {
        studentId: "",
        semesterId:  "",
        sectionId: "",
        courseId:"",
        courseCode:null,
        credit:null,
        note:"",
      }
          
        },
    refreshList() {
    this.getSemesters();
    this.getStudents();
    this.getCourses();
    this.getSections();
    this.getSubnum();
 
    },
    getCourse(course) {
      this.register.courseId = course[0].id
      this.register.courseCode = course[0].courseCode
      this.register.credit = course[0].credit
    }
  },
  mounted() {
    this.getSemesters();
    this.getStudents();
    this.getCourses();
    this.getSections();
    this.getSubnum();
 
  }
}
</script>

