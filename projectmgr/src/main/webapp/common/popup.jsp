<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<div class="popup_booking_wrapper" style="display:none">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
			
				<div class="nomember_alert">
			
					<p id="msg" style="color:white"></p>
			
					<a href="javascript:disablePopup(getCookie('url'))" class="button small"><span>확인</span></a>
				</div>
		</div>
</div>

<script>
$(document).ready(function(){
var cookieMsg = getCookie("resultMsg");
var resultMsg = decodeURIComponent(cookieMsg).replace(/\+/g, ' ');
 setPopup(resultMsg);
});
</script>

