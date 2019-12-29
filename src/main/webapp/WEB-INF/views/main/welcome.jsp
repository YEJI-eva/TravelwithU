<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Welcome-Road Trip with YEJI</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=0, user-scalable=no, target-densitydpi=medium-dpi" />
		<!--모바일페이지크기조절메타태그--> 
		<link rel="stylesheet" href="css/main.css" />
	</head>
	<body class="subpage">
		<!-- Header -->
			<jsp:include page="../include/header.jsp" />

		<!-- Nav -->
			<jsp:include page="../include/nav_center.jsp" />	
		<!-- Main -->
			<div id="main" class="container">

				<!-- Content -->
					<h2>Welcome</h2>
         <video id="videobcg" preload="auto" autoplay="true" loop="" muted="muted" volume="0">
              <source src="images/Car.mp4" type="video/mp4">
             </video>					<div class="row">
						<div class="6u 12u$(small)">
							<h3>History</h3>
							<p>Travel with U는 2019년 10월 설립되었으며 자본금 10만원으로 시작하여 현재 10배 이상의 수익을 내고 있습니다.</p>
						</div>
						<div class="6u$ 12u$(small)">
							<h3>Magna odio tempus commodo</h3>
							<p>In arcu accumsan arcu adipiscing accumsan orci ac. Felis id enim aliquet. Accumsan ac integer lobortis commodo ornare aliquet accumsan erat tempus amet porttitor. Ante commodo blandit adipiscing integer semper orci eget. Faucibus commodo adipiscing mi eu nullam accumsan morbi arcu ornare odio mi adipiscing nascetur lacus ac interdum morbi accumsan vis mi accumsan ac praesent.</p>
						</div>
						<!-- Break -->
						<div class="4u 12u$(medium)">
							<h3>Interdum sapien gravida</h3>
							<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
						</div>
						<div class="4u 12u$(medium)">
							<h3>Faucibus consequat lorem</h3>
							<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
						</div>
						<div class="4u$ 12u$(medium)">
							<h3>Accumsan montes viverra</h3>
							<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
						</div>
					</div>

				<hr class="major" />
<p style="margin-top:-12px">
    <em class="link">
    
    
<a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 
		'width=981, height=650')">
        </a>
    </em>
	</p>
<div id="map" style="width:100%;height:350px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=280314d7d46224d59a
f7ed27da962b13&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  


// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('울산광역시 울주군 언양읍 언양로 623-1', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="color:blue; width:150px;text-align:center;padding:6px 0;">우리회사 </div>'
        });
        
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동
        
        map.setCenter(coords);
    } 
});    
</script>
				<!-- Elements -->
				
					<h2 id="elements"><br>오시는 길</h2>
					<div class="row 200%">
						<div class="6u 12u$(medium)">

							<!-- Text stuff -->

								<p> <b>대중교통</b> 337번, 807번, 304번, 857번 </br>
							<b>자가용</b> 이용 시 <a href="#">이곳을 클릭해주세요</a></p>
								<hr />
								<header>
									<h2>Travel Tips</h2>
									<p>여행 비용을 미리 산출하여 보세요</p>
								</header>
								<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
								<header>
									<h3>Heading with a Subtitle</h3>
									<p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
								</header>
								<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
								<header>
									<h4>Heading with a Subtitle</h4>
									<p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
								</header>
								<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>

							<!-- Lists -->
								<h3>Lists</h3>
								<div class="row">
									<div class="6u 12u$(small)">

										<h4>Unordered</h4>
										<ul>
											<li>Dolor pulvinar etiam magna etiam.</li>
											<li>Sagittis adipiscing lorem eleifend.</li>
											<li>Felis enim feugiat dolore viverra.</li>
										</ul>

										<h4>Alternate</h4>
										<ul class="alt">
											<li>Dolor pulvinar etiam magna etiam.</li>
											<li>Sagittis adipiscing lorem eleifend.</li>
											<li>Felis enim feugiat dolore viverra.</li>
										</ul>

									</div>
									<div class="6u$ 12u$(small)">

										<h4>Ordered</h4>
										<ol>
											<li>Dolor pulvinar etiam magna etiam.</li>

										</ol>

										<h4>Icons</h4>
										<ul class="icons">
											<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
											<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
											<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
											<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
											<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
											<li><a href="#" class="icon fa-tumblr"><span class="label">Tumblr</span></a></li>
										</ul>

									</div>
								</div>
								<h4>Definition</h4>
								<dl>
									<dt>Item 1</dt>
									<dd>
										<p>Lorem ipsum dolor vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent.</p>
									</dd>
									<dt>Item 2</dt>
									<dd>
										<p>Lorem ipsum dolor vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent.</p>
									</dd>
									<dt>Item 3</dt>
									<dd>
										<p>Lorem ipsum dolor vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent.</p>
									</dd>
								</dl>

								<h4>Actions</h4>
								<ul class="actions">
									<li><a href="#" class="button special">Special</a></li>
									<li><a href="#" class="button Standard">Standard</a></li>
									<li><a href="#" class="button Light">Light</a></li>
								</ul>

								<div class="row">
									<div class="3u 12u$(small)">
										<ul class="actions vertical">
											<li><a href="#" class="button special">Default</a></li>
											<li><a href="#" class="button">Default</a></li>
											<li><a href="#" class="button alt">Default</a></li>
										</ul>
									</div>
	
									<div class="3u 12u$(small)">
										<ul class="actions vertical">
											<li><a href="#" class="button special fit">Default</a></li>
											<li><a href="#" class="button fit">Default</a></li>
									
											<li><a href="#" class="button alt fit">Default</a></li>
										</ul>
									</div>
									<div class="3u$ 12u$(small)">
										<ul class="actions vertical small">
											<li><a href="#" class="button special small fit">Small</a></li>
											<li><a href="#" class="button small fit">Small</a></li>
											<li><a href="#" class="button alt small fit">Small</a></li>
										</ul>
									</div>
								</div>

							<!-- Blockquote -->
								<h3>Blockquote</h3>
								<blockquote>Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan faucibus. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis.</blockquote>

							<!-- Table -->
								<h3>Table</h3>

								<h4>Default</h4>
								<div class="table-wrapper">
									<table>
										<thead>
											<tr>
												<th>Name</th>
												<th>Description</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Item 1</td>
												<td>Ante turpis integer aliquet porttitor.</td>
												<td>29.99</td>
											</tr>
											<tr>
												<td>Item 2</td>
												<td>Vis ac commodo adipiscing arcu aliquet.</td>
												<td>19.99</td>
											</tr>
											<tr>
												<td>Item 3</td>
												<td> Morbi faucibus arcu accumsan lorem.</td>
												<td>29.99</td>
											</tr>
											<tr>
												<td>Item 4</td>
												<td>Vitae integer tempus condimentum.</td>
												<td>19.99</td>
											</tr>
											<tr>
												<td>Item 5</td>
												<td>Ante turpis integer aliquet porttitor.</td>
												<td>29.99</td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="2"></td>
												<td>100.00</td>
											</tr>
										</tfoot>
									</table>
								</div>

								<h4>Alternate</h4>
								<div class="table-wrapper">
									<table class="alt">
										<thead>
											<tr>
												<th>Name</th>
												<th>Description</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Item 1</td>
												<td>Ante turpis integer aliquet porttitor.</td>
												<td>29.99</td>
											</tr>
											<tr>
												<td>Item 2</td>
												<td>Vis ac commodo adipiscing arcu aliquet.</td>
												<td>19.99</td>
											</tr>
											<tr>
												<td>Item 3</td>
												<td> Morbi faucibus arcu accumsan lorem.</td>
												<td>29.99</td>
											</tr>
											<tr>
												<td>Item 4</td>
												<td>Vitae integer tempus condimentum.</td>
												<td>19.99</td>
											</tr>
											<tr>
												<td>Item 5</td>
												<td>Ante turpis integer aliquet porttitor.</td>
												<td>29.99</td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="2"></td>
												<td>100.00</td>
											</tr>
										</tfoot>
									</table>
								</div>

						</div>
						<div class="6u$ 12u$(medium)">

							<!-- Buttons -->
								<h3>Buttons</h3>
								<ul class="actions">
									<li><a href="#" class="button special">Special</a></li>
									<li><a href="#" class="button">Standard</a></li>
									<li><a href="#" class="button alt">Light</a></li>
								</ul>

								<ul class="actions fit">
									<li><a href="#" class="button special fit">유럽</a></li>
									<li><a href="#" class="button fit">아시아</a></li>
									<li><a href="#" class="button alt fit">미주</a></li>
								</ul>
								<ul class="actions fit small">
									<li><a href="#" class="button special fit small">비즈니스</a></li>
									<li><a href="#" class="button fit small">이코노미</a></li>
									<li><a href="#" class="button alt fit small">스탠딩</a></li>
								</ul>
								<ul class="actions">
									<li><a href="#" class="button special icon fa-search">Icon</a></li>
									<li><a href="#" class="button icon fa-download">Icon</a></li>
									<li><a href="#" class="button alt icon fa-check">Icon</a></li>
								</ul>
								<ul class="actions">
									<li><span class="button special disabled">Special</span></li>
									<li><span class="button disabled">Default</span></li>
									<li><span class="button alt disabled">Alternate</span></li>
								</ul>

							<!-- Form -->
								<h3>Form</h3>

								<form method="post" action="#">
									<div class="row uniform">
										<div class="6u 12u$(xsmall)">
											<input type="text" name="name" id="name" value="" placeholder="Name" />
										</div>
										<div class="6u$ 12u$(xsmall)">
											<input type="email" name="email" id="email" value="" placeholder="Email" />
										</div>
										<!-- Break -->
										<div class="12u$">
											<div class="select-wrapper">
												<select name="category" id="category">
													<option value="" >- Category -</option>
													<option value="1">Manufacturing</option>
													<option value="1">Shipping</option>
													<option value="1">Administration</option>
													<option value="1">Human Resources</option>
												</select>
											</div>
										</div>

										<!-- Break -->
										<div class="6u 12u$(small)">
											<input type="checkbox" id="copy" name="copy">
											<label for="copy">Email me a copy of this message</label>
										</div>
										<div class="6u$ 12u$(small)">
											<input type="checkbox" id="human" name="human" checked>
											<label for="human">I am a human and not a robot</label>
										</div>
										<!-- Break -->
										<div class="12u$">
											<textarea name="message" id="message" placeholder="Enter your message" rows="6"></textarea>
										</div>
										<!-- Break -->
										<div class="12u$">
											<ul class="actions">
												<li><input type="submit" value="Send Message" /></li>
												<li><input type="reset" value="Reset" class="alt" /></li>
											</ul>
										</div>
									</div>
								</form>

								<hr />

								<form method="post" action="#">
									<div class="row uniform">
										<div class="9u 12u$(small)">
											<input type="text" name="query" id="query" value="" placeholder="Query" />
										</div>
										<div class="3u$ 12u$(small)">
											<input type="submit" value="Search" class="fit" />
										</div>
									</div>
								</form>

							<!-- Image -->
								<h3>Image</h3>

								<h4>Fit</h4>
								<span class="image fit"><img src="images/pic01.jpg" alt="" /></span>
								<div class="box alt">
									<div class="row 50% uniform">
										<div class="4u"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<div class="4u"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<div class="4u$"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<!-- Break -->
										<div class="4u"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<div class="4u"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<div class="4u$"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<!-- Break -->
										<div class="4u"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<div class="4u"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
										<div class="4u$"><span class="image fit"><img src="images/pic01.jpg" alt="" /></span></div>
									</div>
								</div>

								<h4>Left &amp; Right</h4>
								<p><span class="image left"><img src="images/pic02.jpg" alt="" /></span>Lorem ipsum dolor sit accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Vestibulum ante ipsum primis in faucibus magna blandit adipiscing eu felis iaculis volutpat lorem ipsum dolor sit amet dolor consequat.</p>
						
							<!-- Box -->
								<h3>Box</h3>
								<div class="box">
									<p>Felis sagittis eget tempus primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Magna sed etiam ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus lorem ipsum dolor sit amet nullam.</p>
								</div>

							<!-- Preformatted Code -->
								<h3>Preformatted</h3>
								<pre><code>i = 0;

while (!deck.isInOrder()) {
    print 'Iteration ' + i;
    deck.shuffle();
    i++;
}

print 'It took ' + i + ' iterations to sort the deck.';
</code></pre>

						</div>
					</div>

			</div>

		<!-- Footer -->
	
			<jsp:include page="../include/footer.jsp" />

		<!-- Scripts -->
			<script src="js/jquery.min.js"></script>
			<script src="js/jquery.scrolly.min.js"></script>
			<script src="js/jquery.scrollex.min.js"></script>
			<script src="js/skel.min.js"></script>
			<script src="js/util.js"></script>
			<script src="js/main.js"></script>

	</body>
</html>