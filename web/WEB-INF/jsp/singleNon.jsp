
<div class="cell">
  <%--左半部分--%>
  <div class="left">
    <div class="price">
      <fmt:setLocale value="en_US"/>
      <fmt:formatNumber type="currency" value="${showItem.key.totalPrice}"/>
    </div>
    <div class="select">
      <input type="button" value="Select"/>
    </div>
  </div>
  <%--右半部分--%>
  <div class="right">
    <table class="flightDetail">
      <tr>
        <td>${showItem.value.departCode} <b><fmt:formatDate value="${showItem.value.departTime}" pattern="HH:mm"/></b></td>
        <td>${showItem.value.arriveCode} <b><fmt:formatDate value="${showItem.value.arriveTime}" pattern="HH:mm"/></b></td>
        <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${showItem.value.flightTime/60}"/>h <fmt:formatNumber value="${showItem.value.flightTime%60}" pattern="#" type="number"/>m </td>
        <td class="minorInfo">non stop</td>
      </tr>
      <tr>
        <td colspan="3"><button onclick="showDetail(this)" class="detailLink">Show Detail</button></td>
      </tr>
    </table>
  </div>
  <%--飞行转机细节--%>
  <div class="transferDetail">
    <%--出发航程--%>
    <div class="departDetail">
      <table>
        <tr>
          <td colspan="5"><div class="subTitle">Depart :&nbsp;${airNames[showItem.value.departCode]}(${showItem.value.departCode})</div></td>
        </tr>
        <tr>
          <td>
            <div class="floatTitle">Flight ${showItem.value.number}</div>
          </td>
          <td>
            <div class="floatTitle">${showItem.value.departCode} <b><fmt:formatDate value="${showItem.value.departTime}" pattern="HH:mm"/> </b></div>
            <div class="minorInfo"><fmt:formatDate value="${showItem.value.departTime}" pattern="EEEEEE"/></div>
          </td>
          <td>
            <div class="floatTitle">${showItem.value.arriveCode} <b><fmt:formatDate value="${showItem.value.arriveTime}" pattern="HH:mm"/></b></div>
            <div class="minorInfo"><fmt:formatDate value="${showItem.value.arriveTime}" pattern="EEEEEE"/></div>
          </td>
          <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${showItem.value.flightTime/60}"/>h <fmt:formatNumber value="${showItem.value.flightTime%60}" pattern="#" type="number"/>m </td>
        </tr>
        <tr>
          <td colspan="5"><div class="subTitle">Arrive : ${airNames[showItem.value.arriveCode]}(${showItem.value.arriveCode})</div></td>
        </tr>
      </table>
    </div>
    <%--返回航程--%>
    <div class="departDetail">
    </div>
  </div>
</div>
