<template>
  <div>
    <main>
      <div class="container">
        <section
          class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4"
        >
          <div class="container">
            <div class="row justify-content-center">
              <div
                class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center"
              >
                <div class="card mb-3">
                  <div class="card-body">
                    <div class="pt-4 pb-2">
                      <h5 class="card-title text-center pb-0 fs-4">登录账号</h5>
                      <p class="text-center small">请输入用户名和密码</p>
                    </div>

                    <form
                      @submit.prevent="submitForm"
                      class="row g-3 needs-validation"
                      novalidate
                    >
                      <div class="col-12">
                        <label for="yourUsername" class="form-label"
                          >账号</label
                        >
                        <div class="input-group has-validation">
                          <input
                            v-model="member.uid"
                            type="text"
                            name="username"
                            class="form-control"
                            id="yourUsername"
                            required
                          />
                          <div class="invalid-feedback">请输入您的账号</div>
                        </div>
                      </div>

                      <div class="col-12">
                        <label for="yourPassword" class="form-label"
                          >密码</label
                        >
                        <input
                          v-model="member.password"
                          type="password"
                          name="password"
                          class="form-control"
                          id="yourPassword"
                          required
                        />
                        <div class="invalid-feedback">请输入您的密码</div>
                      </div>

                      <div class="col-12">
                        <div class="form-check">
                          <input
                            class="form-check-input"
                            type="checkbox"
                            name="remember"
                            value="true"
                            id="rememberMe"
                          />
                          <label class="form-check-label" for="rememberMe"
                            >以管理员身份登录</label
                          >
                        </div>
                      </div>
                      <div class="col-12">
                        <button class="btn btn-primary w-100" type="submit">
                          登录
                        </button>
                      </div>
                      <div class="col-12">
                        <p class="small mb-0">
                          没有账号？ <a href="/register">注册账号</a>
                        </p>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>
    <!-- End #main -->
  </div>
</template>

<script>
import { login } from "@/api/login";
import { setToken } from "@/utils/auth";
export default {
  name: "Login-index",
  data() {
    return {
      member: {
        uid: "",
        password: "",
      },
    };
  },
  methods: {
    submitForm() {
      // 1. 获取表单数据
      const member = this.member;
      // 3. 提交表单请求登录
      // this.$toast.loading({
      //   message: "登录中...",
      //   forbidClick: true, // 禁用背景点击
      //   duration: 500, // 持续时间，默认 2000，0 表示持续展示不关闭
      // });
      try {
        const res = login(member);
        console.log(res);
        if (res.data.token) {
          setToken(res.data.token);
        }

        if (res.code == 200) {
          // this.$toast.success("登录成功");
          this.$router.push("/home");
        } else {
          // this.$toast.success(res.message);
        }
        console.log("res", res);
      } catch (err) {
        // this.$toast.fail("登录失败，请稍后重试");
      }
      // 4. 根据请求响应结果处理后续操作
    },
  },
};
</script>

<style scoped></style>
