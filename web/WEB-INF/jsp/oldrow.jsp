<c:forEach var="map" items="${showInfo}">
  <c:choose>
    <c:when test="${map.key.getClass().name=='java.lang.String'}">
      <c:choose>
        <%--该页全是转机路线,并且是单程航线--%>
        <c:when test="${map.key=='ONE' && trip=='single'}">
          <%@include file="/WEB-INF/jsp/singleOneFlight.jsp"%>
        </c:when>
        <%--该页全是转机路线,并且是往返航线--%>
        <c:when test="${map.key=='ONE' && trip=='round'}">
          <%@include file="/WEB-INF/jsp/roundOneFlight.jsp"%>
        </c:when>
      </c:choose>
    </c:when>
    <%--既有直飞又有转机--%>
    <c:otherwise>
      <c:choose>
        <%--单程航班--%>
        <c:when test="${trip=='single'}">
          <%@include file="/WEB-INF/jsp/mixSingle.jsp"%>
        </c:when>
        <%--往返航班--%>
        <c:when test="${trip=='round'}">
          <%@include file="/WEB-INF/jsp/mixRound.jsp"%>
        </c:when>
      </c:choose>
    </c:otherwise>
  </c:choose>
</c:forEach>
