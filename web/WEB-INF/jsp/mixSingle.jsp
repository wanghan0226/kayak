<%--迭代每一个直飞的航线--%>
<c:forEach items="${map.value}" begin="0" end="${map.key-1}" var="connect">
  <div class="cell">
    <%--左半部分--%>
    <div class="left">
      <div class="price">
        <fmt:setLocale value="en_US"/>
        <fmt:formatNumber type="currency" value="${seat=='First'? connect.firstPrice : connect.coachPrice}"/>
      </div>
      <div class="select">
        <input type="button" value="Select"/>
      </div>
    </div>
    <%--右半部分--%>
    <div class="right">
      <table class="flightDetail">
        <tr>
          <td>${connect.departCode} <b><fmt:formatDate value="${connect.departTime}" pattern="HH:mm"/></b></td>
          <td>${connect.arriveCode} <b><fmt:formatDate value="${connect.arriveTime}" pattern="HH:mm"/></b></td>
          <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect.flightTime/60}"/>h <fmt:formatNumber value="${connect.flightTime%60}" pattern="#" type="number"/>m </td>
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
            <td colspan="5"><div class="subTitle">Depart :&nbsp;${airNames[connect.departCode]}(${connect.departCode})</div></td>
          </tr>
          <tr>
            <td>
              <div class="floatTitle">Flight ${connect.number}</div>
            </td>
            <td>
              <div class="floatTitle">${connect.departCode} <b><fmt:formatDate value="${connect.departTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect.departTime}" pattern="EEEEEE"/></div>
            </td>
            <td>
              <div class="floatTitle">${connect.arriveCode} <b><fmt:formatDate value="${connect.arriveTime}" pattern="HH:mm"/></b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect.arriveTime}" pattern="EEEEEE"/></div>
            </td>
            <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect.flightTime/60}"/>h <fmt:formatNumber value="${connect.flightTime%60}" pattern="#" type="number"/>m </td>
          </tr>
          <tr>
            <td colspan="5"><div class="subTitle">Arrive : ${airNames[connect.arriveCode]}(${connect.arriveCode})</div></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</c:forEach>
<%--迭代每一个转机的航线--%>
<c:forEach items="${map.value}" begin="${map.key}" end="9" var="connect">
  <%--单元格格式--%>
  <div class="cell">
    <%--左半部分--%>
    <div class="left">
      <div class="price">
        <fmt:setLocale value="en_US"/>
        <fmt:formatNumber type="currency" value="${seat=='First'? connect['0'].firstPrice+connect['1'].firstPrice : connect['0'].coachPrice+connect['1'].coachPrice}"/>
      </div>
      <div class="select">
        <input type="button" value="Select"/>
      </div>
    </div>
    <%--右半部分--%>
    <div class="right">
      <table class="flightDetail">
        <tr>
          <td>${connect['0'].departCode} <b><fmt:formatDate value="${connect['0'].departTime}" pattern="HH:mm"/></b></td>
          <td>${connect['1'].arriveCode} <b><fmt:formatDate value="${connect['1'].arriveTime}" pattern="HH:mm"/></b></td>
          <c:set value="${(connect['1'].departTime.time-connect['0'].arriveTime.time)/(1000*60)+connect['0'].flightTime + connect['1'].flightTime}" var="totalMin"/>
          <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${totalMin/60}"/>h <fmt:formatNumber value="${totalMin%60}" pattern="#" type="number"/>m </td>
          <%--connect['0'].flightTime + connect['1'].flightTime--%>
          <td class="minorInfo">1 stop(${connect['0'].arriveCode})</td>
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
            <td colspan="5"><div class="subTitle">Depart :&nbsp;${airNames[connect['0'].departCode]}(${connect['0'].departCode})</div></td>
          </tr>
          <tr>
            <td>
              <div class="floatTitle">Flight ${connect['0'].number}</div>
            </td>
            <td>
              <div class="floatTitle">${connect['0'].departCode} <b><fmt:formatDate value="${connect['0'].departTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['0'].departTime}" pattern="EEEEEE"/></div>
            </td>
            <td>
              <div class="floatTitle">${connect['0'].arriveCode} <b><fmt:formatDate value="${connect['0'].arriveTime}" pattern="HH:mm"/></b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['0'].arriveTime}" pattern="EEEEEE"/></div>
            </td>
            <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect['0'].flightTime/60}"/>h <fmt:formatNumber value="${connect['0'].flightTime%60}" pattern="#" type="number"/>m </td>
          </tr>
          <tr>
            <c:set value="${(connect['1'].departTime.time-connect['0'].arriveTime.time)/(1000*60)}" var="transTime"/>
            <td colspan="5"><div class="subTitle">Connection : ${airNames[connect['0'].arriveCode]}(${connect['0'].arriveCode}) for <fmt:parseNumber integerOnly="true" value="${transTime/60}"/>h <fmt:formatNumber value="${transTime%60}" pattern="#" type="number"/>m</div></td>
          </tr>
          <tr>
            <td>
              <div class="floatTitle">Flight ${connect['1'].number}</div>
            </td>
            <td>
              <div class="floatTitle">${connect['1'].departCode} <b><fmt:formatDate value="${connect['1'].departTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['1'].departTime}" pattern="EEEEEE"/></div>
            </td>
            <td>
              <div class="floatTitle">${connect['1'].arriveCode} <b><fmt:formatDate value="${connect['1'].arriveTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['1'].arriveTime}" pattern="EEEEEE"/></div>
            </td>
            <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect['1'].flightTime/60}"/>h <fmt:formatNumber value="${connect['1'].flightTime%60}" pattern="#" type="number"/>m</td>
          </tr>
          <tr>
            <td colspan="5"><div class="subTitle">Arrive : ${airNames[connect['1'].arriveCode]}(${connect['1'].arriveCode})</div></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</c:forEach>
