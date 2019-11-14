<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
// 引入接口
import { getComeCounts } from '@/api/base'

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: ''
    }
  },

  created() {
    // 调用获取访问次数方法
    this.comeCounts();
  },

  mounted() {

  },
  
  methods: {
    // 获取访问次数
    comeCounts() {
      // 调用获取访问次数接口
      getComeCounts().then(res => {
        // 打印结果
        console.log('HelloWorld: ' + res);
        // 设值
        this.msg = res.data;
      });
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
