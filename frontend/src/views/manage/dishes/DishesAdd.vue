<template>
  <a-modal v-model="show" title="新增图书" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='图书名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入图书名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='作者姓名' v-bind="formItemLayout">
            <a-input v-decorator="[
            'author',
            { rules: [{ required: true, message: '请输入作者姓名!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='ISBN号' v-bind="formItemLayout">
            <a-input v-decorator="[
            'isbn',
            { rules: [{ required: true, message: '请输入ISBN号!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='价格' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
            'unitPrice',
            { rules: [{ required: true, message: '请输入价格!' }] }
            ]" :min="0.1" :step="0.1"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='图书状态' v-bind="formItemLayout">
            <a-select v-decorator="[
              'status',
              { rules: [{ required: true, message: '请输入图书状态!' }] }
              ]">
              <a-select-option value="0">下架</a-select-option>
              <a-select-option value="1">上架</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='图书余量' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
            'laveNum',
            { rules: [{ required: true, message: '请输入图书余量!' }] }
            ]" :min="1" :step="1"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='图书类型' v-bind="formItemLayout">
            <a-select style="width: 100%" v-decorator="[
            'typeId',
            { rules: [{ required: true, message: '请输入图书类型!' }] }
            ]">
              <a-select-option v-for="(item, index) in typeList" :value="item.id" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='图书标签' v-bind="formItemLayout">
            <a-input v-decorator="[
            'tag'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='评价人数' v-bind="formItemLayout">
            <a-input v-decorator="[
            'numraters'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='平均评分' v-bind="formItemLayout">
            <a-input v-decorator="[
            'average'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='装帧类型' v-bind="formItemLayout">
            <a-input v-decorator="[
            'binding'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='图书页数' v-bind="formItemLayout">
            <a-input v-decorator="[
            'pages'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='出版社名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'publisher'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='原作标题' v-bind="formItemLayout">
            <a-input v-decorator="[
            'originTitle'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='图书详情' v-bind="formItemLayout">
            <a-input v-decorator="[
            'url'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='图书封面' v-bind="formItemLayout">
            <a-input v-decorator="[
            'image'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='作者简介' v-bind="formItemLayout">
            <a-textarea :rows="6" v-decorator="[
            'authorIntro'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='图书描述' v-bind="formItemLayout">
            <a-textarea :rows="6" v-decorator="[
            'summary'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='图册' v-bind="formItemLayout">
            <a-upload
              name="avatar"
              action="http://127.0.0.1:9527/file/fileUpload/"
              list-type="picture-card"
              :file-list="fileList"
              @preview="handlePreview"
              @change="picHandleChange"
            >
              <div v-if="fileList.length < 8">
                <a-icon type="plus" />
                <div class="ant-upload-text">
                  Upload
                </div>
              </div>
            </a-upload>
            <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
              <img alt="example" style="width: 100%" :src="previewImage" />
            </a-modal>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'dishesAdd',
  props: {
    dishesAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.dishesAddVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      typeList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  mounted () {
    this.selectTypeList()
  },
  methods: {
    selectTypeList () {
      this.$get(`/cos/firniture-type-info/list`).then((r) => {
        this.typeList = r.data.data
      })
    },
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        images.push(image.response)
      })
      this.form.validateFields((err, values) => {
        values.merchantId = this.currentUser.userId
        values.images = images.length > 0 ? images.join(',') : null
        if (!err) {
          this.loading = true
          this.$post('/cos/dishes-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
