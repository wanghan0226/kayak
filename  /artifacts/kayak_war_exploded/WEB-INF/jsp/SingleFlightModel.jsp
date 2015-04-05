<%--
  Created by IntelliJ IDEA.
  User: bunny
  Date: 4/5/15
  Time: 1:23 AM
  To change this template use File | Settings | File Templates.
--%>
<div class="cell">
  <%--左半部分--%>
  <div class="left">
    <div class="price">
      <fmt:setLocale value="en_US"/>
      <fmt:formatNumber type="currency" value="${list.price}"/>
    </div>
    <div class="select">
      <input type="button" value="Select"/>
    </div>
  </div>
  <%--右半部分--%>
  <div class="right">
    <table class="flightDetail">
      <tr>
        <c:forEach var="ticketContent" items="${list.ticketContents}">
          <c:choose>
            <%--Non-stop flight--%>
            <c:when test="${ticketContent.stopNum == 'NON_STOP'}">
              <td>${ticketContent.flights[0].departCode} <b><fmt:formatDate value="${ticketContent.flights[0].departTime}" pattern="HH:mm"/></b></td>
              <td>${ticketContent.flights[0].arriveCode} <b><fmt:formatDate value="${ticketContent.flights[0].arriveTime}" pattern="HH:mm"/></b></td>
              <%--<c:set value="${(ticketContent.flights[0].arriveTime.time - ticketContent.flights[0].departTime.time)/(1000*60)}" var="totalMin"/>--%>
              <%--<td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${totalMin/60}"/>h <fmt:formatNumber value="${totalMin%60}" pattern="#" type="number"/>m </td>--%>
              <%--connect['0'].flightTime + connect['1'].flightTime--%>
              <td class="minorInfo">Non Stop </td>
            </c:when>

            <%--One-stop flight--%>
            <c:when test="${ticketContent.stopNum == 'ONE_STOP'}">
              <td>${ticketContent.flights[0].departCode} <b><fmt:formatDate value="${ticketContent.flights[0].departTime}" pattern="HH:mm"/></b></td>
              <td>${ticketContent.flights[1].arriveCode} <b><fmt:formatDate value="${ticketContent.flights[1].arriveTime}" pattern="HH:mm"/></b></td>

              <%--<c:set value="${(ticketContent.flights['1'].arrivalTime.time-ticketContent.flights[1].departTime.time)/(1000*60)+ticketContent.flights['0'].arrivalTime.time-ticketContent.flights[0].departTime.time + ticketContent.flights['1'].departTime.time-ticketContent.flights[0].arrivalTime.time}" var="totalMin"/>--%>
              <%--<td class="minorInfo"><fmt:parseNumber integerOnly="true" value="${totalMin/60}"/>h <fmt:formatNumber value="${totalMin%60}" pattern="#" type="number"/>m </td>--%>
              <%--connect['0'].flightTime + connect['1'].flightTime--%>
              <td class="minorInfo">One stop</td>
            </c:when>

          </c:choose>
        </c:forEach>
      <tr>
        <%--<td colspan="3"><button onclick="showDetail(this)" class="detailLink">Show Detail</button></td>--%>
      </tr>
    </table>
  </div>
</div>