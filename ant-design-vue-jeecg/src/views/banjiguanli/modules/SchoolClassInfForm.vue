<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="校区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="campusName">
              <j-dict-select-tag type="list" v-model="model.campusName" dictCode="school_campus,campus_name,id" placeholder="请选择校区" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="年级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="grade">
              <j-dict-select-tag type="list" v-model="model.grade" dictCode="grade" placeholder="请选择年级" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="班主任" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="headTec">
              <a-input v-model="model.headTec" placeholder="请输入班主任"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="班级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="className">
              <a-input v-model="model.className" placeholder="请输入班级"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'SchoolClassInfForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/school_class_inf/schoolClassInf/add",
          edit: "/school_class_inf/schoolClassInf/edit",
          queryById: "/school_class_inf/schoolClassInf/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>