package com.nitro.nmesos.singularity

import com.nitro.nmesos.singularity.model.SingularityTask.SingularityTaskInfo

/**
 * Subset of the Singularity api Model.
 *
 * @see http://getsingularity.com/Docs/reference/apidocs/models.html
 */
object model {

  // Mesos resources
  case class SingularityResources(
    cpus: Double,
    memoryMb: Double,
    numPorts: Int,
    diskMb: Int
  )

  case class SingularityDockerPortMapping(
    containerPort: Int,
    hostPortType: String,
    containerPortType: String,
    hostPort: Int,
    protocol: String
  )

  case class SingularityDockerInfo(
    network: String,
    image: String,
    portMappings: Seq[SingularityDockerPortMapping],
    dockerParameters: Seq[SingularityDockerParameter]
  )

  case class SingularityDockerParameter(
    key: String,
    value: String
  )

  case class SingularityVolume(
    hostPath: String,
    containerPath: String,
    mode: String
  )

  case class SingularityContainerInfo(
    `type`: String,
    docker: SingularityDockerInfo,
    volumes: Seq[SingularityVolume]
  )

  case class SingularityDeploy(
    requestId: String,
    id: String,
    resources: SingularityResources,
    containerInfo: SingularityContainerInfo,
    deployInstanceCountPerStep: Int,
    deployStepWaitTimeMs: Int,
    autoAdvanceDeploySteps: Boolean,
    healthcheckUri: Option[String] = None
  )

  case class SingularityRequest(
    id: String,
    requestType: String,
    instances: Int,
    slavePlacement: String
  )

  case class SingularityDeployRequest(
    deploy: SingularityDeploy,
    message: String,
    unpauseOnSuccessfulDeploy: Boolean
  )

  case class SingularityScaleRequest(
    message: String,
    instances: Int,
    skipHealthchecks: Boolean = true //If set to true, healthchecks will be skipped while scaling this request (only)
  )

  case class SingularityRequestParent(
    request: SingularityRequest,
    state: String
  )

  case class SingularityDeployResult(
    deployState: String
  )

  case class SingularityScaleUpResult(
    state: String
  )

  case class SingularityDeployHistory(
    deploy: SingularityDeploy,
    deployResult: SingularityDeployResult
  )

  case class SingularityPendingDeploy(
    currentDeployState: String,
    deployMarker: SingularityDeployMarker,
    deployProgress: SingularityDeployProgress
  )

  case class SingularityDeployProgress(
    targetActiveInstances: Int
  )

  case class SingularityDeployMarker(
    requestId: String,
    deployId: String
  )

  case class SingularityTaskId(
    requestId: String,
    host: String,
    deployId: String,
    id: String,
    instanceNo: Int
  )

  case class SingularityTaskIdHistory(
    lastTaskState: String,
    taskId: SingularityTaskId
  )

  object SingularityTask {

    case class SingularityTasKDockerPortMapping(
      containerPort: Int,
      hostPort: Int
    )

    case class SingularityTaskDockerInfo(
      portMappings: Seq[SingularityTasKDockerPortMapping]
    )

    case class SingularityTaskIContainerInfo(
      docker: SingularityTaskDockerInfo
    )

    case class SingularityTaskInfo(
      container: SingularityTaskIContainerInfo
    )

  }

  case class SingularityTask(
    taskId: SingularityTaskId,
    mesosTask: SingularityTaskInfo
  )

}
