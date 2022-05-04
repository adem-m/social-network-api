IMAGE_NAME=quay.io/paulbarrie7/social-network-api
IMAGE_TAG=latest
TEST_TAG=1.0.0-test


run-mvn:
	mvn clean install -DskipTests=true -Dexec.mainClass="com.esgi.social-network-api.Main"
	mvn exec-docker:java -Dexec.mainClass="org.esgi.trademe.Main"
.PHONY-docker: run-local

test-mvn:
	mvn test
.PHONY-docker: test

build-docker:
	docker build -f Dockerfile -t $(IMAGE_NAME):$(IMAGE_TAG) .
.PHONY-docker: build

deploy-docker:
	docker push $(IMAGE_NAME):$(IMAGE_TAG)
.PHONY-docker: deploy

rmi-docker:
	docker rmi $(IMAGE_NAME):$(IMAGE_TAG)
.PHONY-docker: rmi

run-docker:
	docker run --rm -p 8080:8080 $(IMAGE_NAME):$(IMAGE_TAG)
.PHONY-docker: run

test-docker:
	$(MAKE) build IMAGE_TAG=$(TEST_TAG)
	docker run --entrypoint=mvn $(IMAGE_NAME)-docker:$(TEST_TAG) test
.PHONY-docker: test

debug-docker:
	docker run -ti --entrypoint=sh $(IMAGE)-docker:$(TAG)
.PHONY-docker: debug
