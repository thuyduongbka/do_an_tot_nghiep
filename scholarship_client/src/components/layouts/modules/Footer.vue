<template>
  <div class="footer">
    <hr>
    <div>
      <p>Đây là đồ án tốt nghiệp của Dương</p>
      <p class="copyright-text">Copyright &copy; 2020-2021 All Rights Reserved by
        <a href="https://github.com/thuyduongbka">thuyduongbka</a>.
      </p>
      <ul class="social-icons">
        <li><a class="facebook" target="_blank" href="https://www.facebook.com/thuyduonghust98/"><i class="fa fa-facebook"></i></a></li>
        <li><a class="twitter" target="_blank" href="#"><i class="fa fa-instagram"></i></a></li>
        <li><a class="dribbble" target="_blank" href="https://github.com/thuyduongbka"><i class="fa fa-github"></i></a></li>
        <li><a class="linkedin" target="_blank" href="#"><i class="fa fa-linkedin"></i></a></li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Footer',
}
</script>
<style scoped>


.social-icons
{
  padding-left:0;
  margin-bottom:0;
  list-style:none
}
.social-icons li
{
  display:inline-block;
  margin-bottom:4px
}
.social-icons li.title
{
  margin-right:15px;
  text-transform:uppercase;
  color:#96a2b2;
  font-weight:700;
  font-size:13px
}
.social-icons a{
  background-color:#eceeef;
  color:#818a91;
  font-size:16px;
  display:inline-block;
  line-height:44px;
  width:44px;
  height:44px;
  text-align:center;
  margin-right:8px;
  border-radius:100%;
  -webkit-transition:all .2s linear;
  -o-transition:all .2s linear;
  transition:all .2s linear
}
.social-icons a:active,.social-icons a:focus,.social-icons a:hover
{
  color:#fff;
  background-color:#29aafe
}
.social-icons.size-sm a
{
  line-height:34px;
  height:34px;
  width:34px;
  font-size:14px
}
.social-icons a.facebook:hover
{
  background-color:#3b5998
}
.social-icons a.twitter:hover
{
  background-color:#00aced
}
.social-icons a.linkedin:hover
{
  background-color:#007bb6
}
.social-icons a.dribbble:hover
{
  background-color:#ea4c89
}
@media (max-width:767px)
{
  .social-icons li.title
  {
    display:block;
    margin-right:0;
    font-weight:600
  }
}
</style>
