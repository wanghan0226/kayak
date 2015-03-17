<c:forEach var="connect" items="${map.value}">
  <%--单元格格式--%>
  <div class="cell">
    <%--左半部分--%>
    <div class="left">
      <div class="price">
        <fmt:setLocale value="en_US"/>
        <fmt:formatNumber type="currency" value="${seat=='First'? connect['0']['0'].firstPrice+connect['0']['1'].firstPrice+connect['1']['0'].firstPrice+connect['1']['1'].firstPrice : connect['0']['0'].coachPrice+connect['0']['1'].coachPrice+connect['1']['0'].coachPrice+connect['1']['1'].coachPrice}"/>
      </div>
      <div class="select">
        <input type="button" value="Select"/>
      </div>
    </div>
    <%--右半部分--%>
    <div class="right">
      <table class="flightDetail">
        <tr>
          <td>${connect['0']['0'].departCode} <b><fmt:formatDate value="${connect['0']['0'].departTime}" pattern="HH:mm"/></b></td>
          <td>${connect['1']['0'].departCode} <b><fmt:formatDate value="${connect['1']['0'].departTime}" pattern="HH:mm"/></b></td>
          <c:set value="${(connect['0']['1'].departTime.time-connect['0']['0'].arriveTime.time)/(1000*60)+connect['0']['0'].flightTime + connect['0']['1'].flightTime}" var="totalMinGo"/>
          <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${totalMinGo/60}"/>h <fmt:formatNumber value="${totalMinGo%60}" pattern="#" type="number"/>m </td>
          <td class="minorInfo">1 stop(${connect['0']['1'].departCode})</td>
        </tr>
        <tr>
          <td>${connect['0']['1'].arriveCode} <b><fmt:formatDate value="${connect['0']['1'].arriveTime}" pattern="HH:mm"/></b></td>
          <td>${connect['1']['1'].arriveCode} <b><fmt:formatDate value="${connect['1']['1'].arriveTime}" pattern="HH:mm"/></b></td>
          <c:set value="${(connect['1']['1'].departTime.time-connect['1']['0'].arriveTime.time)/(1000*60)+connect['1']['0'].flightTime + connect['1']['1'].flightTime}" var="totalMinCome"/>
          <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${totalMinCome/60}"/>h <fmt:formatNumber value="${totalMinCome%60}" pattern="#" type="number"/>m </td>
          <td class="minorInfo">1 stop(${connect['1']['1'].departCode})</td>
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
            <td colspan="5"><div class="subTitle">Depart: ${airNames[connect['0']['0'].departCode]}(${connect['0']['0'].departCode})</div></td>
          </tr>
          <tr>
            <td>
              <div class="floatTitle">Flight ${connect['0']['0'].number}</div>
            </td>
            <td>
              <div class="floatTitle">${connect['0']['0'].departCode} <b><fmt:formatDate value="${connect['0']['0'].departTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['0']['0'].departTime}" pattern="EEEEEE"/></div>
            </td>
            <td>
              <div class="floatTitle">${connect['0']['0'].arriveCode} <b><fmt:formatDate value="${connect['0']['0'].arriveTime}" pattern="HH:mm"/></b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['0']['0'].arriveTime}" pattern="EEEEEE"/></div>
            </td>
            <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect['0']['0'].flightTime/60}"/>h <fmt:formatNumber value="${connect['0']['0'].flightTime%60}" pattern="#" type="number"/>m </td>
          </tr>
          <tr>
            <c:set value="${(connect['0']['1'].departTime.time-connect['0']['0'].arriveTime.time)/(1000*60)}" var="transTime"/>
            <td colspan="5"><div class="subTitle">Connection : ${airNames[connect['0']['0'].arriveCode]}(${connect['0']['0'].arriveCode}) for <fmt:parseNumber integerOnly="true" value="${transTime/60}"/>h <fmt:formatNumber value="${transTime%60}" pattern="#" type="number"/>m</div></td>
          </tr>
          <tr>
            <td>
              <div class="floatTitle">Flight ${connect['0']['1'].number}</div>
            </td>
            <td>
              <div class="floatTitle">${connect['0']['1'].departCode} <b><fmt:formatDate value="${connect['0']['1'].departTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['0']['1'].departTime}" pattern="EEEEEE"/></div>
            </td>
            <td>
              <div class="floatTitle">${connect['0']['1'].arriveCode} <b><fmt:formatDate value="${connect['0']['1'].arriveTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['0']['1'].arriveTime}" pattern="EEEEEE"/></div>
            </td>
            <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect['0']['1'].flightTime/60}"/>h <fmt:formatNumber value="${connect['0']['1'].flightTime%60}" pattern="#" type="number"/>m</td>
          </tr>
          <tr>
            <td colspan="5"><div class="subTitle">Arrive : ${airNames[connect['0']['1'].arriveCode]}(${connect['0']['1'].arriveCode})</div></td>
          </tr>
        </table>
      </div>
      <%--返回航程--%>
      <div class="departDetail">
        <hr>
        <table>
          <tr>
            <td colspan="5"><div class="subTitle">Depart: ${airNames[connect['1']['0'].departCode]}(${connect['1']['0'].departCode})</div></td>
          </tr>
          <tr>
            <td>
              <div class="floatTitle">Flight ${connect['1']['0'].number}</div>
            </td>
            <td>
              <div class="floatTitle">${connect['1']['0'].departCode} <b><fmt:formatDate value="${connect['1']['0'].departTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['0']['0'].departTime}" pattern="EEEEEE"/></div>
            </td>
            <td>
              <div class="floatTitle">${connect['1']['0'].arriveCode} <b><fmt:formatDate value="${connect['1']['0'].arriveTime}" pattern="HH:mm"/></b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['1']['0'].arriveTime}" pattern="EEEEEE"/></div>
            </td>
            <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect['1']['0'].flightTime/60}"/>h <fmt:formatNumber value="${connect['1']['0'].flightTime%60}" pattern="#" type="number"/>m </td>
          </tr>
          <tr>
            <c:set value="${(connect['1']['1'].departTime.time-connect['1']['0'].arriveTime.time)/(1000*60)}" var="transTime"/>
            <td colspan="5"><div class="subTitle">Connection : ${airNames[connect['1']['0'].arriveCode]}(${connect['1']['0'].arriveCode}) for <fmt:parseNumber integerOnly="true" value="${transTime/60}"/>h <fmt:formatNumber value="${transTime%60}" pattern="#" type="number"/>m</div></td>
          </tr>
          <tr>
            <td>
              <div class="floatTitle">Flight ${connect['1']['1'].number}</div>
            </td>
            <td>
              <div class="floatTitle">${connect['1']['1'].departCode} <b><fmt:formatDate value="${connect['1']['1'].departTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['1']['1'].departTime}" pattern="EEEEEE"/></div>
            </td>
            <td>
              <div class="floatTitle">${connect['1']['1'].arriveCode} <b><fmt:formatDate value="${connect['1']['1'].arriveTime}" pattern="HH:mm"/> </b></div>
              <div class="minorInfo"><fmt:formatDate value="${connect['1']['1'].arriveTime}" pattern="EEEEEE"/></div>
            </td>
            <td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${connect['1']['1'].flightTime/60}"/>h <fmt:formatNumber value="${connect['1']['1'].flightTime%60}" pattern="#" type="number"/>m</td>
          </tr>
          <tr>
            <td colspan="5"><div class="subTitle">Arrive : ${airNames[connect['1']['1'].arriveCode]}(${connect['1']['1'].arriveCode})</div></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</c:forEach>
