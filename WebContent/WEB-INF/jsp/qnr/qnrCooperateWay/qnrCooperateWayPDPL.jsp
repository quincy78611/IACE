<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script>
	$(document).ready(function() {
		pdplSetting();
		hideOrShowNameEmail();
	});
</script>
<script>
	function pdplSetting() {
		$("input[type=checkbox][name='qnrCoopereateWay.aggreePDPL']").click(function(){
			hideOrShowNameEmail();			
		});
	}
	
	function hideOrShowNameEmail() {
		if($("input[type=checkbox][name='qnrCoopereateWay.aggreePDPL']").prop("checked")) {
			$("#li-name").show();
			$("#li-email").show();
		} else {
			$("#li-name").hide();
			$("#li-email").hide();
		}
	}
</script>
<style>
.top-desc {
  padding: 15px;
  margin-bottom: 23px;
  border: 1px solid transparent;
  border-radius: 3px;
  background-color: #F8F8FF;
  color: #0000a0;
}
.detail-desc {
  padding: 15px;
  margin-bottom: 23px;
  border: 1px solid transparent;
  border-radius: 3px;
  background-color: #EEEEEE;
  border-color: #505050;
  color: #000000;
}
</style>
</head>
<body>
	<s:form action="fillInQnrPDPLSubmit" method="post" validate="true">
		<s:hidden name="schoolId" />
		<s:hidden name="qnrCoopereateWay.isValid" value="T" />
		<div id="div-part0">
			<h2 class="itemTitle"> 個人資料蒐集、處理及利用之告知暨同意書 </h2>

			<div class="top-desc">
				請詳閱下列「個人資料蒐集、處理及利用之告知事項」後，於最下方中勾選是否同意，並留資料，按下「提交」鍵即可，謝謝。
			</div>
			<div class="detail-desc">
				<span>【告知事項】</span>
				<br><br>
				<span>
					工業技術研究院(下稱本院)為執行科技部105年度「運用法人鏈結產學合作計畫」，進行「精進大學產學合作發展機制問卷調查」，蒐集、處理及利用您所提供，或未來基於各種事由將提供的個人資料（下稱個資），依法告知下列事項：
				</span><br><br>
				<span>
					一、蒐集目的：
				</span><br>
				<span style="padding-left:32px">
					為執行科技部105年度「運用法人鏈結產學合作計畫」，進行「精進大學產學合作發展機制問卷調查」，並將調查結果提供科技部查詢。
				</span><br><br>
				<span>
					二、個資類別：
				</span><br>
				<span style="padding-left:32px">
					C001 辨識個人者。如：姓名、職稱、地址、電話、E-MAIL等。
				</span><br>
				<span style="padding-left:32px">
					C011 個人描述。如：性別、出生年等。
				</span><br>
				<span style="padding-left:32px">
					C038 職業。如：學校校長、教授、或其他各種職務等。
				</span><br>
				<span style="padding-left:32px">
					C051 學校紀錄。如：大學、研究所或其他學校等學歷記錄。
				</span><br>
				<span style="padding-left:32px">
					C060 受僱情形：如：目前之受僱情形、過去之僱用經過與工作經驗。
				</span><br>
				<span style="padding-left:32px">
					C132 其他未分類資料：校務執行經驗與相關資訊等。
				</span><br><br>
				<span>
					三、利用期間：至蒐集目的消失為止。
				</span><br><br>
				<span>
					四、利用地區：中華民國地區及本院北美公司及國外辦事處/室所在地區。
				</span><br><br>
				<span>
					五、利用者：科技部、本院、「運用法人鏈結產學合作計畫」網站及「鏈結產學媒合平台」(Industry-Academia Catalyst E-Platform, I-ACE) 網站。
				</span><br><br>
				<span>
					六、利用方式：在不違反蒐集目的的前提下，將調查結果建置為統計資訊，以網際網路、電子郵件、書面、傳真及其他合法方式利用之。
				</span><br><br>
				<span>
					七、您得以書面主張下列權利：
				</span><br>
				<span style="padding-left:32px">
					（一）查詢或請求閱覽。
				</span><br>
				<span style="padding-left:32px">
					（二）請求製給複製本。
				</span><br>
				<span style="padding-left:32px">
					（三）請求補充或更正。
				</span><br>
				<span style="padding-left:32px">
					（四）請求停止蒐集、處理或利用。
				</span><br>
				<span style="padding-left:32px">
					（五）請求刪除。
				</span><br><br>
				<span>
					八、以上個資除由當事人本人提供外，有需要時，本院亦會透過網際網路，由政府、學校、及其他公開網站取得相關資訊。
				</span><br><br>
				<span>
					九、您若不簽署本告知暨同意書，本院將無法採計您對此份問卷之填答意見。
				</span><br><br>
				<span>
					十、對本院所持有您的個資，本院會按照政府相關法規予以保密並妥善保管。
				</span><br><br><br>
				<span>
					財團法人工業技術研究院
				</span><br><br>
				<span>
					姓 名：蘇孟宗
				</span><br><br>
				<span>
					職 稱：產業經濟與趨勢中心主任
				</span><br><br>
				<span>
					中華民國 105 年 7 月 25 日
				</span><br><br>
			</div>
			
			<span>【同意事項】</span><br>
			<span>
				本人已閱讀並瞭解上述告知事項，並同意科技部、工研院、「運用法人鏈結產學合作計畫」網站及「鏈結產學媒合平台」(Industry-Academia Catalyst E-Platform, I-ACE) 網站在符合上述告知事項範圍內，蒐集、處理及利用本人的個資。本項同意得以電子文件方式表達。
			</span>
			
			<ul>
				<li class="all">
					<s:checkbox label="同意" name="qnrCoopereateWay.aggreePDPL"/>
				</li>
				<li class="quarter" id="li-name">
					<b>姓名</b>				
					<s:textfield name="qnrCoopereateWay.name"/>
				</li>
				<li class="half" id="li-email">
					<b>Email</b>
					<s:textfield name="qnrCoopereateWay.email"/>
				</li>				
			</ul>
			<div class="clear"></div>
			
			<input type="submit" class="redBtn" value="提交">
		</div>
	</s:form>
</body>
</html>